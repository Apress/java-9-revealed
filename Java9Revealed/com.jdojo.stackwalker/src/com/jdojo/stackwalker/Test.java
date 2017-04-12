// Test.java

package com.jdojo.stackwalker;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import static java.util.stream.Collectors.joining;

public class Test {
    public static void main(String[] args) {
      m1();      
    }
    
    public static void m1() {        
        m2();        
    }
    
    public static void m2() {
        // Call m3() directly
        System.out.println("\nWithout using reflection: ");
        m3();
        
        // Call m3() using reflection        
        try {
            System.out.println("\nUsing reflection: ");
            Test.class.getMethod("m3").invoke(null);
        } catch (NoSuchMethodException |  
                 InvocationTargetException | 
                 IllegalAccessException | 
                 SecurityException e) {
            e.printStackTrace();
        }        
    }
    
    public static void m3() {
        // Prints the call stack details
        String stackStr = StackWalker.getInstance()
                                     .walk(s -> s.map(f -> f.toString())
                                                 .collect(joining(System.getProperty("line.separator"))));
        System.out.println(stackStr);
        
        
    }
    
}
