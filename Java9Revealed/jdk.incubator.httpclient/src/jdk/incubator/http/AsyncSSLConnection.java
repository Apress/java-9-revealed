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
import java.util.function.Consumer;
import java.util.function.Supplier;

import jdk.incubator.http.internal.common.ByteBufferReference;
import jdk.incubator.http.internal.common.ExceptionallyCloseable;
import jdk.incubator.http.internal.common.Utils;

/**
 * Asynchronous version of SSLConnection.
 */
class AsyncSSLConnection extends HttpConnection
                         implements AsyncConnection, ExceptionallyCloseable {

    final AsyncSSLDelegate sslDelegate;
    final PlainHttpConnection delegate;

    AsyncSSLConnection(InetSocketAddress addr, HttpClientImpl client, String[] ap) {
        super(addr, client);
        delegate = new PlainHttpConnection(addr, client);
        sslDelegate = new AsyncSSLDelegate(delegate, client, ap);
    }

    @Override
    synchronized void configureMode(Mode mode) throws IOException {
        super.configureMode(mode);
        delegate.configureMode(mode);
    }

    @Override
    public void connect() throws IOException, InterruptedException {
        delegate.connect();
    }

    @Override
    public CompletableFuture<Void> connectAsync() {
        return delegate.connectAsync();
    }

    @Override
    boolean connected() {
        return delegate.connected();
    }

    @Override
    boolean isSecure() {
        return true;
    }

    @Override
    boolean isProxied() {
        return false;
    }

    @Override
    SocketChannel channel() {
        return delegate.channel();
    }

    @Override
    ConnectionPool.CacheKey cacheKey() {
        return ConnectionPool.cacheKey(address, null);
    }

    @Override
    long write(ByteBuffer[] buffers, int start, int number)
        throws IOException
    {
        ByteBuffer[] bufs = Utils.reduce(buffers, start, number);
        long n = Utils.remaining(bufs);
        sslDelegate.writeAsync(ByteBufferReference.toReferences(bufs));
        sslDelegate.flushAsync();
        return n;
    }

    @Override
    long write(ByteBuffer buffer) throws IOException {
        long n = buffer.remaining();
        sslDelegate.writeAsync(ByteBufferReference.toReferences(buffer));
        sslDelegate.flushAsync();
        return n;
    }

    @Override
    public void writeAsyncUnordered(ByteBufferReference[] buffers) throws IOException {
        assert getMode() == Mode.ASYNC;
        sslDelegate.writeAsyncUnordered(buffers);
    }

    @Override
    public void writeAsync(ByteBufferReference[] buffers) throws IOException {
        assert getMode() == Mode.ASYNC;
        sslDelegate.writeAsync(buffers);
    }

    @Override
    public void flushAsync() throws IOException {
        sslDelegate.flushAsync();
    }

    @Override
    public void closeExceptionally(Throwable cause) {
        Utils.close(cause, sslDelegate, delegate.channel());
    }

    @Override
    public void close() {
        Utils.close(sslDelegate, delegate.channel());
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
    public void setAsyncCallbacks(Consumer<ByteBufferReference> asyncReceiver,
                                  Consumer<Throwable> errorReceiver,
                                  Supplier<ByteBufferReference> readBufferSupplier) {
        sslDelegate.setAsyncCallbacks(asyncReceiver, errorReceiver, readBufferSupplier);
        delegate.setAsyncCallbacks(sslDelegate::asyncReceive, errorReceiver, sslDelegate::getNetBuffer);
    }

    // Blocking read functions not used here

    @Override
    protected ByteBuffer readImpl() throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    protected int readImpl(ByteBuffer buffer) throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    CompletableFuture<Void> whenReceivingResponse() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void startReading() {
        delegate.startReading();
        sslDelegate.startReading();
    }
}
