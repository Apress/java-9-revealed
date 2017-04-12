// SetTest.java
package com.jdojo.collection;

import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        // Create few unmodifiable sets
        Set<Integer> emptySet = Set.of();
        Set<Integer> luckyNumber = Set.of(19);
        Set<String> vowels = Set.of("A", "E", "I", "O", "U");
        
        System.out.println("emptySet = " + emptySet);
        System.out.println("singletonSet = " + luckyNumber);
        System.out.println("vowels = " + vowels);
                
        try {
            // Try using a null element
            Set<Integer> set = Set.of(1, 2, null, 3);
        } catch(NullPointerException e) {
            System.out.println("Nulls not allowed in Set.of().");
        }
        
        try {
            // Try using duplicate elements
            Set<Integer> set = Set.of(1, 2, 3, 2);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
