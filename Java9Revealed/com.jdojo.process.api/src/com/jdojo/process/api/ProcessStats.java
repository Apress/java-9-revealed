// ProcessStats.java
package com.jdojo.process.api;

import java.time.Duration;
import java.time.Instant;

public class ProcessStats {
    public static void main(String[] args) {
        System.out.printf("Longest CPU User Process:%n");
        ProcessHandle.allProcesses()
                     .max(ProcessStats::compareCpuTime)
                     .ifPresent(CurrentProcessInfo::printInfo);
        
        System.out.printf("%nLongest Running Process:%n");
        ProcessHandle.allProcesses()
                     .max(ProcessStats::compareStartTime)
                     .ifPresent(CurrentProcessInfo::printInfo);
    }

    public static int compareCpuTime(ProcessHandle ph1, 
                                     ProcessHandle ph2) {
        return ph1.info()
                .totalCpuDuration()
                .orElse(Duration.ZERO)
                .compareTo(ph2.info()
                        .totalCpuDuration()
                        .orElse(Duration.ZERO));
    }
        
     public static int compareStartTime(ProcessHandle ph1, 
                                        ProcessHandle ph2) {
        return ph1.info()
                .startInstant()
                .orElse(Instant.now())
                .compareTo(ph2.info()
                        .startInstant()
                        .orElse(Instant.now()));
    }
}
