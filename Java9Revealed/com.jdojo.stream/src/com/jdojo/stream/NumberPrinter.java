// NumberPrinter.java
package com.jdojo.stream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.LongStream;

public class NumberPrinter {
    public static void main(String[] args) {        
        CompletableFuture<Void> subTask = null;
        
        // The publisher is closed when the try block exits
        try (SubmissionPublisher<Long> pub = new SubmissionPublisher<>()) {
            // Print the buffer size used for each subscriber
            System.out.println("Subscriber Buffer Size: " + pub.getMaxBufferCapacity());

            // Add a subscriber to the publisher. The subscriber prints the published elements
            subTask = pub.consume(System.out::println);

            // Generate and publish five integers
            LongStream.range(1L, 6L)
                      .forEach(pub::submit);
        }

        if (subTask != null) {
            try {
                 // Wait until the subscriber is complete
                subTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
