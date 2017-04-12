// CallerClassTest.java
package com.jdojo.stackwalker;

import java.lang.StackWalker.Option;
import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;
import static java.lang.StackWalker.Option.SHOW_REFLECT_FRAMES;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CallerClassTest {
    public static void main(String[] args) {
        /* Will not be able to get caller class because because the RETAIN_CLASS_REFERENCE 
           option is not specified.
        */
        m1(Set.of());

        // Will print the caller class
        m1(Set.of(RETAIN_CLASS_REFERENCE, SHOW_REFLECT_FRAMES));

        try {
            /* The following statement will throw an IllegalStateException if this class is run
               because there will be no caller class; JVM will call this method. However, 
               if the main() method is called in code, no exception will be thrown.            
            */
            Class<?> cls = StackWalker.getInstance(RETAIN_CLASS_REFERENCE)
                                      .getCallerClass();
            System.out.println("In main method, Caller Class: " + cls.getName());
        } catch (IllegalCallerException e) {
            System.out.println("In main method, Exception: " + e.getMessage());
        }
    }

    public static void m1(Set<Option> options) {
        m2(options);
    }

    public static void m2(Set<Option> options) {
        // Call m3() using reflection
        try {
            CallerClassTest.class
                           .getMethod("m3", Set.class)
                           .invoke(null, options);
        } catch (NoSuchMethodException | InvocationTargetException
                | IllegalAccessException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public static void m3(Set<Option> options) {
        try {
            // Print the caller class
            Class<?> cls = StackWalker.getInstance(options)                  
                                      .getCallerClass();
            System.out.println("Caller Class: " + cls.getName());
        } catch (UnsupportedOperationException e) {
            System.out.println("Inside m3(): " + e.getMessage());
        }
    }
}
