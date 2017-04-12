// JDeprScanTest.java
package com.jdojo.deprecation;

public class JDeprScanTest {
    public static void main(String[] args) {
        Thread t = new Thread(
            () -> System.out.println("Test"));
        t.start();
        t.stop();
        
        Thread t2 = new Thread(
            () -> System.out.println("Test"));
        t2.start();
        t2.destroy();
    }
}