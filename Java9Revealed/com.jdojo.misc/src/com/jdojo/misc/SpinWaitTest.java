// SpinWaitTest.java
package com.jdojo.misc;

public class SpinWaitTest implements Runnable {
    private volatile boolean dataReady = false;

    @Override
    public void run() {
        // Wait while data is ready
        while (!dataReady) {
            // Hint a spin-wait
            Thread.onSpinWait();
        }

        processData();
    }

    private void processData() {
        // Data processing logic goes here
    }

    public void setDataReady(boolean dataReady) {
        this.dataReady = dataReady;
    }
}
