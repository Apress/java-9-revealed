// CurrentProcessInfo.java
package com.jdojo.process.api;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class CurrentProcessInfo {
    public static void main(String[] args) {
        // Get the handle of the current process
        ProcessHandle current = ProcessHandle.current();
        
        // Print the process details
        printInfo(current);
    }    
        
    public static void printInfo(ProcessHandle handle) {
        // Get the process ID
        long pid = handle.getPid();
        
        // Is the process still running
        boolean isAlive = handle.isAlive();
        
        // Get other process info
        ProcessHandle.Info info = handle.info();
        String command = info.command().orElse("");
        String[] args = info.arguments()
                            .orElse(new String[]{});
        String commandLine = info.commandLine().orElse("");
        ZonedDateTime startTime = info.startInstant()
                             .orElse(Instant.now())
                             .atZone(ZoneId.systemDefault());
        Duration duration = info.totalCpuDuration()
                                .orElse(Duration.ZERO);
        String owner = info.user().orElse("Unknown");
        long childrenCount = handle.children().count();

        // Print the process details
        System.out.printf("PID: %d%n", pid);        
        System.out.printf("IsAlive: %b%n", isAlive);
        System.out.printf("Command: %s%n", command);
        System.out.printf("Arguments: %s%n",
                           Arrays.toString(args));
        System.out.printf("CommandLine: %s%n", commandLine);
        System.out.printf("Start Time: %s%n", startTime);
        System.out.printf("CPU Time: %s%n", duration);
        System.out.printf("Owner: %s%n", owner);
        System.out.printf("Children Count: %d%n", 
                          childrenCount);
    }
}
