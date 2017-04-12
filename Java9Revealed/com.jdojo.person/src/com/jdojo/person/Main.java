// Main.java
package com.jdojo.person;

import com.jdojo.address.Address;

public class Main {
    public static void main(String[] args) {
        Person john = new Person(1001, "John", "Jacobs");

        String fName = john.getFirstName();
        String lName = john.getLastName();
        Address addr = john.getAddress();

        System.out.printf("%s %s%n", fName, lName);
        System.out.printf("%s%n", addr.getLine1());
        System.out.printf("%s, %s %s%n", addr.getCity(), 
                          addr.getState(), addr.getZip());
    }
}
