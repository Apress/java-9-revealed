// Main.java
package com.jdojo.person.test;

import com.jdojo.person.Person;

public class Main {
    public static void main(String[] args) {
        Person john = new Person(1001, "John", "Jacobs");
        
        // Get John's city and print it
        String city = john.getAddress().getCity();
        System.out.printf("John lives in %s%n", city);
    }    
}
