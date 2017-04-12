// StartProcessTest.java
package com.jdojo.process.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StartProcessTest {
    public static void main(String[] args) {
        // Start a process that runs for 15 seconds
        Process p = Job.startProcess(5, 15);

        if (p == null) {
            System.out.println("Could not create a" +
                               " new process.");
            return;
        }
        
        // Get the handle of the current process
        ProcessHandle handle = p.toHandle();

        // Print the process details
        CurrentProcessInfo.printInfo(handle);

        CompletableFuture<ProcessHandle> future =
            handle.onExit();

        // Prints a message when process terminates
        future.thenAccept((ProcessHandle ph) -> {
            System.out.printf("Job (pid=%d) terminated.%n", 
                              ph.getPid());
        });

        try {
            // Wait for the process to complete
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Print process details again
        CurrentProcessInfo.printInfo(handle);
    }
}
