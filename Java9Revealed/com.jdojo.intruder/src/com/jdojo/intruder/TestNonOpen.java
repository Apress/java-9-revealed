// TestNonOpen.java
package com.jdojo.intruder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestNonOpen {
    public static void main(String[] args) 
            throws IllegalAccessException, IllegalArgumentException, 
            NoSuchMethodException, ClassNotFoundException, 
            InvocationTargetException, InstantiationException, 
            NoSuchFieldException {
        
        String className = "com.jdojo.address.Address";

        // Get the class reference
        Class<?> cls = Class.forName(className);

        // Get the no-args constructor
        Constructor constructor = cls.getConstructor();

        // Create an Object of the Address class
        Object address = constructor.newInstance();
        
        // Call the getLine1() method to get the line1 value
        Method getLine1Ref = cls.getMethod("getLine1");
        String line1 = (String)getLine1Ref.invoke(address);
        System.out.println("Using method reference, Line1: " + line1);

        // Use the private line1 instance variable to read its value
        Field line1Field = cls.getDeclaredField("line1");
        line1Field.setAccessible(true);
        String line11 = (String)line1Field.get(address);
        System.out.println("Using private field reference, Line1: " + line11);
    }
}
