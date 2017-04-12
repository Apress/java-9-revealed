// StrictMathTest.java
package com.jdojo.misc;

import static java.lang.StrictMath.*;

public class StrictMathTest {
    public static void main(String[] args) {
        System.out.println("Using StrictMath.floorDiv(long, int):");
        System.out.printf("floorDiv(20L, 3) = %d%n", floorDiv(20L, 3));
        System.out.printf("floorDiv(-20L, -3) = %d%n", floorDiv(-20L, -3));
        System.out.printf("floorDiv(-20L, 3) = %d%n", floorDiv(-20L, 3));
        System.out.printf("floorDiv(Long.Min_VALUE, -1) = %d%n", floorDiv(Long.MIN_VALUE, -1));

        System.out.println("\nUsing StrictMath.floorMod(long, int):");
        System.out.printf("floorMod(20L, 3) = %d%n", floorMod(20L, 3));
        System.out.printf("floorMod(-20L, -3) = %d%n", floorMod(-20L, -3));
        System.out.printf("floorMod(-20L, 3) = %d%n", floorMod(-20L, 3));

        System.out.println("\nUsing StrictMath.fma(double, double, double):");
        System.out.printf("fma(3.337, 6.397, 2.789) = %f%n", fma(3.337, 6.397, 2.789));

        System.out.println("\nUsing StrictMath.multiplyExact(long, int):");
        System.out.printf("multiplyExact(29087L, 7897979) = %d%n",
                multiplyExact(29087L, 7897979));
        try {
            System.out.printf("multiplyExact(Long.MAX_VALUE, 5) = %d%n", 
                    multiplyExact(Long.MAX_VALUE, 5));
        } catch (ArithmeticException e) {
            System.out.println("multiplyExact(Long.MAX_VALUE, 5) = " + e.getMessage());
        }

        System.out.println("\nUsing StrictMath.multiplyFull(int, int):");
        System.out.printf("multiplyFull(29087, 7897979) = %d%n", multiplyFull(29087, 7897979));

        System.out.println("\nUsing StrictMath.multiplyHigh(long, long):");
        System.out.printf("multiplyHigh(29087L, 7897979L) = %d%n", 
                multiplyHigh(29087L, 7897979L));
        System.out.printf("multiplyHigh(Long.MAX_VALUE, 8) = %d%n", 
                multiplyHigh(Long.MAX_VALUE, 8));
    }
}
