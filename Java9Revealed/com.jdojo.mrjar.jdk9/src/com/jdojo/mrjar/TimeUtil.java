// TimeUtil.java
package com.jdojo.mrjar;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class TimeUtil {
    public TimeUtil() {
        System.out.println("Creating JDK 9 version of TimeUtil...");
    }
    
    public LocalDate getLocalDate(Instant now) {
        return LocalDate.ofInstant(now, ZoneId.systemDefault()); 
    }
}
