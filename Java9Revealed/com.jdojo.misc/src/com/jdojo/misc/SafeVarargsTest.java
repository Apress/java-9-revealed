// SafeVarargsTest.java
package com.jdojo.misc;

public class SafeVarargsTest {
    // Allowed in JDK 9
    @SafeVarargs
    private <T> void print(T... args) {
        for(T element : args) {
            System.out.println(element);
        }
    }
    
    // More code goes here
}
