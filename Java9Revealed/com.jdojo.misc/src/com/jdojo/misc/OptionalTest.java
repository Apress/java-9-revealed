// OptionalTest.java
package com.jdojo.misc;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

public class OptionalTest {
    public static void main(String[] args) {
        // Create a list of Optional<Integer>
        List<Optional<Integer>> optionalList = List.of(
                Optional.of(1),
                Optional.empty(),
                Optional.of(2),
                Optional.empty(),
                Optional.of(3));

        // Print the original list
        System.out.println("Original List: " + optionalList);
        
        // Using the ifPresentOrElse() method
        optionalList.stream()
                    .forEach(p -> p.ifPresentOrElse(System.out::println, 
                                                    () -> System.out.println("Empty")));
        
        // Using the or() method
        optionalList.stream()
                    .map(p -> p.or(() -> Optional.of(0)))
                    .forEach(System.out::println);
        
        // In Java 8
        List<Integer> list8 = optionalList.stream()
                                          .filter(Optional::isPresent)
                                          .map(Optional::get)
                                          .collect(toList());
        System.out.println("List in Java 8: " + list8);

        // In Java 9
        List<Integer> list9 = optionalList.stream()
                                          .flatMap(Optional::stream)
                                          .collect(toList());
        System.out.println("List in Java 9: " + list9);
    }
}