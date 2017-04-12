// Test.java
package com.jdojo.collection;

import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // Creating an empty immutable List before JDK 9
        List<Integer> emptyList1 = Collections.EMPTY_LIST;
        List<Integer> emptyList2 = Collections.emptyList();
                
        // Creating an empty list in JDK 9
        List<Integer> emptyList = List.of();

        // Creating a simglton immutable List before JDK 9
        List<Integer> singltonList1 = Collections.singletonList(100);
        
        // Creating a simglton immutable List in JKD 9
        List<Integer> singltonList = List.of(100);
        
    }
}
