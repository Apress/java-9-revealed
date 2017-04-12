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

import java.time.Duration;
import java.time.Instant;

/**
 * Timeout event notified by selector thread. Executes the given handler if
 * the timer not cancelled first.
 *
 * Register with {@link HttpClientImpl#registerTimer(TimeoutEvent)}.
 *
 * Cancel with {@link HttpClientImpl#cancelTimer(TimeoutEvent)}.
 */
abstract class TimeoutEvent implements Comparable<TimeoutEvent> {

    private final Instant deadline;

    TimeoutEvent(Duration duration) {
        deadline = Instant.now().plus(duration);
    }

    public abstract void handle();

    public Instant deadline() {
        return deadline;
    }

    @Override
    public int compareTo(TimeoutEvent other) {
        return this.deadline.compareTo(other.deadline);
    }
}
