// MapTest.java
package com.jdojo.collection;

import java.util.Map;
import static java.util.Map.entry;

public class MapTest {
    public static void main(String[] args) {
        // Create few unmodifiable maps
        Map<Integer,String> emptyMap = Map.of();
        Map<Integer,String> luckyNumber = Map.of(19, "Nineteen");
        Map<Integer,String> numberToWord = Map.of(1, "One", 2, "Two", 3, "Three");

        Map<String,String> days = Map.ofEntries(
                entry("Mon", "Monday"),
                entry("Tue", "Tuesday"),
                entry("Wed", "Wednesday"),
                entry("Thu", "Thursday"),
                entry("Fri", "Friday"),
                entry("Sat", "Saturday"),
                entry("Sun", "Sunday"));
        
        System.out.println("emptyMap = " + emptyMap);
        System.out.println("singletonMap = " + luckyNumber);
        System.out.println("numberToWord = " + numberToWord);
        System.out.println("days = " + days);
                
        try {
            // Try using a null value
            Map<Integer,String> map = Map.of(1, null);
        } catch(NullPointerException e) {
            System.out.println("Nulls not allowed in Map.of().");
        }
        
        try {
            // Try using duplicate keys
            Map<Integer,String> map = Map.of(1, "One", 1, "On");
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            // Try adding an entry
            luckyNumber.put(8, "Eight");
        } catch(UnsupportedOperationException e) {
            System.out.println("Cannot add an entry.");
        }
        
         try {
            // Try removing an entry
            luckyNumber.remove(0);
        } catch(UnsupportedOperationException e) {
            System.out.println("Cannot remove an entry.");
        }
    }
}
