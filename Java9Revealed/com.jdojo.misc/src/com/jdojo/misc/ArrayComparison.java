// ArrayComparison.java
package com.jdojo.misc;

import java.util.Arrays;

public class ArrayComparison {
    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = {1, 2, 7, 4, 5};
        int[] a3 = {1, 2, 3, 4, 5};
        
        // Print original arrays
        System.out.println("Three arrays:");
        System.out.println("a1: " + Arrays.toString(a1));
        System.out.println("a2: " + Arrays.toString(a2));
        System.out.println("a3: " + Arrays.toString(a3));
        
        // Compare arrays for equality
        System.out.println("\nComparing arrays using equals() method:");
        System.out.println("Arrays.equals(a1, a2): " + Arrays.equals(a1, a2));
        System.out.println("Arrays.equals(a1, a3): " + Arrays.equals(a1, a3));
        System.out.println("Arrays.equals(a1, 0, 2, a2, 0, 2): " + 
                           Arrays.equals(a1, 0, 2, a2, 0, 2));
                       
        // Compare arrays lexicographically
        System.out.println("\nComparing arrays using compare() method:");
        System.out.println("Arrays.compare(a1, a2): " + Arrays.compare(a1, a2));
        System.out.println("Arrays.compare(a2, a1): " + Arrays.compare(a2, a1));
        System.out.println("Arrays.compare(a1, a3): " + Arrays.compare(a1, a3));
        System.out.println("Arrays.compare(a1, 0, 2, a2, 0, 2): " + 
                           Arrays.compare(a1, 0, 2, a2, 0, 2));
        
        // Find the mismatched index in arrays
        System.out.println("\nFinding mismatch using the mismatch() method:");                
        System.out.println("Arrays.mismatch(a1, a2): " + Arrays.mismatch(a1, a2));
        System.out.println("Arrays.mismatch(a1, a3): " + Arrays.mismatch(a1, a3));
        System.out.println("Arrays.mismatch(a1, 0, 5, a2, 0, 1): " + 
                            Arrays.mismatch(a1, 0, 5, a2, 0, 1));
    }
}
