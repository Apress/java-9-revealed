// ReflectTest.java
package com.jdojo.reflect.test;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

public class ReflectTest {
    public static void main(String[] args) 
            throws ClassNotFoundException {
        // Get the Class object for the com.jdojo.reflect.Item class
        // which is in the com.jdjo.reflect module
        Class<?> cls = Class.forName("com.jdojo.reflect.Item");

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            printFieldValue(field);
        }
    }

    public static void printFieldValue(Field field) {
        String fieldName = field.getName();
        try {                       
            // Make the field accessible, in case it is not accessible
            // based on its decalration such as a private field
            field.setAccessible(true);            
            
            // Print the field's value
            System.out.println(fieldName + " = " + field.get(null));
        } catch (IllegalAccessException | IllegalArgumentException | 
                 InaccessibleObjectException e) {
            System.out.println("Accessing " + fieldName + 
                               ". Error: " + e.getMessage());           
        }
    }
}
