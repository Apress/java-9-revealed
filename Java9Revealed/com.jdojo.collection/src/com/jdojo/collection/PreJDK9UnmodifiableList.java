// PreJDK9UnmodifiableList.java
package com.jdojo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreJDK9UnmodifiableList {
    public static void main(String[] args) {
       List<Integer> list = new ArrayList<>(); 
       list.add(100);
       list.add(200);
       System.out.println("list = " + list);
       
       // Create an unmodifiable list
       List<Integer> list2 =
           Collections.unmodifiableList(list);

       System.out.println("list2 = " + list2);
       
       // Let us add an element using list
       list.add(300);
       
       // Print the contentsof the list using both 
       // variables list and list2
       System.out.println("list = " + list);
       System.out.println("list2 = " + list2);
    }
}
