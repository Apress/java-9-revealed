// CallerClassTest2.java
package com.jdojo.stackwalker;

import java.lang.StackWalker.Option;
import java.util.Set;
import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

public class CallerClassTest2 {
    public static void main(String[] args) {
        Set<Option> options = Set.of(RETAIN_CLASS_REFERENCE);
        CallerClassTest.m1(options);
        CallerClassTest.m2(options);
        CallerClassTest.m3(options);
        
        System.out.println("\nCalling the main() method:");
        CallerClassTest.main(null);
        
        System.out.println("\nUsing an anonymous class:");
        new Object() {
            {
                CallerClassTest.m3(options);
            }   
        };

        System.out.println("\nUsing a lambda expression:");
        new Thread(() -> CallerClassTest.m3(options))
            .start();
        
    }
}
