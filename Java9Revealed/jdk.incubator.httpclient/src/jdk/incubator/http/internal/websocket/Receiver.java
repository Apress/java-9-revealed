/*
 * Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.incubator.http.internal.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/*
 * Receives incoming data from the channel on demand and converts it into a
 * stream of WebSocket messages which are then delivered to the supplied message
 * consumer in a strict sequential order and non-recursively. In other words,
 *
 *     onText()
 *     onText()
 *     onBinary()
 *     ...
 *
 * instead of
 *
 *     onText()
 *       onText()
 *         onBinary()
 *     ...
 *
 * even if `request(long n)` is called from inside these invocations.
 */
final class Receiver {

    private final MessageStreamConsumer messageConsumer;
    private final RawChannel channel;
    private final FrameConsumer frameConsumer;
    private final Frame.Reader reader = new Frame.Reader();
    private final RawChannel.RawEvent event = createHandler();
    private final AtomicLong demand = new AtomicLong();
    private final CooperativeHandler receiveHandler =
              new CooperativeHandler(this::tryDeliver);
    /*
     * Used to ensure registering the channel event at most once (i.e. to avoid
     * multiple registrations).
     */
    private final AtomicBoolean readable = new AtomicBoolean();
    private ByteBuffer data;

    Receiver(MessageStreamConsumer messageConsumer, RawChannel channel) {
        this.messageConsumer = messageConsumer;
        this.channel = channel;
        this.data = channel.initialByteBuffer();
        this.frameConsumer = new FrameConsumer(this.messageConsumer);
        // To ensure the initial `data` will be read correctly (happens-before)
        // after readable.get()
        readable.set(true);
    }

    private RawChannel.RawEvent createHandler() {
        return new RawChannel.RawEvent() {

            @Override
            public int interestOps() {
                return SelectionKey.OP_READ;
            }

            @Override
            public void handle() {
                readable.set(true);
                receiveHandler.startOrContinue();
            }
        };
    }

    void request(long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("Negative: " + n);
        }
        demand.accumulateAndGet(n, (p, i) -> p + i < 0 ? Long.MAX_VALUE : p + i);
        receiveHandler.startOrContinue();
    }

    void acknowledge() {
        long x = demand.decrementAndGet();
        if (x < 0) {
            throw new InternalError(String.valueOf(x));
        }
    }

    /*
     * Stops the machinery from reading and delivering messages permanently,
     * regardless of the current demand.
     */
    void close() {
        receiveHandler.stop();
    }

    private void tryDeliver() {
        if (readable.get() && demand.get() > 0) {
            deliverAtMostOne();
        }
    }

    private void deliverAtMostOne() {
        if (data == null) {
            try {
                data = channel.read();
            } catch (IOException e) {
                readable.set(false);
                messageConsumer.onError(e);
                return;
            }
            if (data == null || !data.hasRemaining()) {
                readable.set(false);
                if (!data.hasRemaining()) {
                    try {
                        channel.registerEvent(event);
                    } catch (IOException e) {
                        messageConsumer.onError(e);
                        return;
                    }
                } else if (data == null) {
                    messageConsumer.onComplete();
                }
                return;
            }
        }
        try {
            reader.readFrame(data, frameConsumer);
        } catch (FailWebSocketException e) {
            messageConsumer.onError(e);
            return;
        }
        if (!data.hasRemaining()) {
            data = null;
        }
    }
}
