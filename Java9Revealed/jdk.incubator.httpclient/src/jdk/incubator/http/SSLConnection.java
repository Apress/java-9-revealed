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

package jdk.incubator.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CompletableFuture;
import javax.net.ssl.SSLEngineResult.Status;
import javax.net.ssl.SSLParameters;
import jdk.incubator.http.SSLDelegate.WrapperResult;

import jdk.incubator.http.internal.common.ByteBufferReference;
import jdk.incubator.http.internal.common.MinimalFuture;
import jdk.incubator.http.internal.common.Utils;

/**
 * An SSL connection built on a Plain TCP connection.
 */
class SSLConnection extends HttpConnection {

    PlainHttpConnection delegate;
    SSLDelegate sslDelegate;
    final String[] alpn;

    @Override
    public CompletableFuture<Void> connectAsync() {
        return delegate.connectAsync()
                .thenCompose((Void v) -> {
                    CompletableFuture<Void> cf = new MinimalFuture<>();
                    try {
                        this.sslDelegate = new SSLDelegate(delegate.channel(),
                                                           client,
                                                           alpn);
                        cf.complete(null);
                    } catch (IOException e) {
                        cf.completeExceptionally(e);
                    }
                    return cf;
                });
    }

    @Override
    public void connect() throws IOException {
        delegate.connect();
        this.sslDelegate = new SSLDelegate(delegate.channel(), client, alpn);
    }

    SSLConnection(InetSocketAddress addr, HttpClientImpl client, String[] ap) {
        super(addr, client);
        this.alpn = ap;
        delegate = new PlainHttpConnection(addr, client);
    }

    @Override
    SSLParameters sslParameters() {
        return sslDelegate.getSSLParameters();
    }

    @Override
    public String toString() {
        return "SSLConnection: " + super.toString();
    }

    private static long countBytes(ByteBuffer[] buffers, int start, int length) {
        long c = 0;
        for (int i=0; i<length; i++) {
            c+= buffers[start+i].remaining();
        }
        return c;
    }

    @Override
    ConnectionPool.CacheKey cacheKey() {
        return ConnectionPool.cacheKey(address, null);
    }

    @Override
    long write(ByteBuffer[] buffers, int start, int number) throws IOException {
        //debugPrint("Send", buffers, start, number);
        long l = countBytes(buffers, start, number);
        WrapperResult r = sslDelegate.sendData(buffers, start, number);
        if (r.result.getStatus() == Status.CLOSED) {
            if (l > 0) {
                throw new IOException("SSLHttpConnection closed");
            }
        }
        return l;
    }

    @Override
    long write(ByteBuffer buffer) throws IOException {
        //debugPrint("Send", buffer);
        long l = buffer.remaining();
        WrapperResult r = sslDelegate.sendData(buffer);
        if (r.result.getStatus() == Status.CLOSED) {
            if (l > 0) {
                throw new IOException("SSLHttpConnection closed");
            }
        }
        return l;
    }

    @Override
    void writeAsync(ByteBufferReference[] buffers) throws IOException {
        write(ByteBufferReference.toBuffers(buffers), 0, buffers.length);
    }

    @Override
    void writeAsyncUnordered(ByteBufferReference[] buffers) throws IOException {
        write(ByteBufferReference.toBuffers(buffers), 0, buffers.length);
    }

    @Override
    void flushAsync() throws IOException {
        // nothing to do
    }

    @Override
    public void close() {
        Utils.close(delegate.channel());
    }

    @Override
    void shutdownInput() throws IOException {
        delegate.channel().shutdownInput();
    }

    @Override
    void shutdownOutput() throws IOException {
        delegate.channel().shutdownOutput();
    }

    @Override
    protected ByteBuffer readImpl() throws IOException {
        ByteBuffer dst = ByteBuffer.allocate(8192);
        int n = readImpl(dst);
        if (n > 0) {
            return dst;
        } else if (n == 0) {
            return Utils.EMPTY_BYTEBUFFER;
        } else {
            return null;
        }
    }

    @Override
    protected int readImpl(ByteBuffer buf) throws IOException {
        // TODO: need to ensure that buf is big enough for application data
        WrapperResult r = sslDelegate.recvData(buf);
        // TODO: check for closure
        String s = "Receive) ";
        //debugPrint(s, r.buf);
        if (r.result.bytesProduced() > 0) {
            assert buf == r.buf;
        }
        return r.result.bytesProduced();
    }

    @Override
    boolean connected() {
        return delegate.connected();
    }

    @Override
    SocketChannel channel() {
        return delegate.channel();
    }

    @Override
    CompletableFuture<Void> whenReceivingResponse() {
        return delegate.whenReceivingResponse();
    }

    @Override
    boolean isSecure() {
        return true;
    }

    @Override
    boolean isProxied() {
        return false;
    }

}
