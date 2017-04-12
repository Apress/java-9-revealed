// LegacyStackWalk.java
package com.jdojo.stackwalker;

import java.lang.reflect.InvocationTargetException;

public class LegacyStackWalk {
    public static void main(String[] args) {
        m1();
    }
    
    public static void m1() {
        m2();
    }
    
    public static void m2() {
        // Call m3() directly
        System.out.println("\nWithout using reflection: ");
        m3();
        
        // Call m3() using reflection        
        try {
            System.out.println("\nUsing reflection: ");
            LegacyStackWalk.class
                         .getMethod("m3")
                         .invoke(null);
        } catch (NoSuchMethodException |  
                 InvocationTargetException | 
                 IllegalAccessException | 
                 SecurityException e) {
            e.printStackTrace();
        }        
    }
    
    public static void m3() {
        // Prints the call stack details
        StackTraceElement[] frames = Thread.currentThread()
                                           .getStackTrace();
        
        for(StackTraceElement frame : frames) {
            System.out.println(frame.toString()); 
        }
    }
}
