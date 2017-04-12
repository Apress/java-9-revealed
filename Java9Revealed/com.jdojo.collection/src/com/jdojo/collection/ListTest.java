// ListTest.java
package com.jdojo.collection;

import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        // Create few unmodifiable lists
        List<Integer> emptyList = List.of();
        List<Integer> luckyNumber = List.of(19);
        List<String> vowels = List.of("A", "E", "I", "O", "U");
        
        System.out.println("emptyList = " + emptyList);
        System.out.println("singletonList = " + luckyNumber);
        System.out.println("vowels = " + vowels);
        
        try {
            // Try using a null element
            List<Integer> list = List.of(1, 2, null, 3);
        } catch(NullPointerException e) {
            System.out.println("Nulls not allowed in List.of().");
        }
        
        try {
            // Try adding an element
            luckyNumber.add(8);
        } catch(UnsupportedOperationException e) {
            System.out.println("Cannot add an element.");
        }
        
         try {
            // Try removing an element
            luckyNumber.remove(0);
        } catch(UnsupportedOperationException e) {
            System.out.println("Cannot remove an element.");
        }
    }
}
