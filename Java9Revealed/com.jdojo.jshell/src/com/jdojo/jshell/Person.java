// Person.java
package com.jdojo.jshell;

public class Person {
    private String name;
    
    public Person() {
        this.name = "Unknown";
    }
            
    public Person(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}
