// StackWalking.java
package com.jdojo.stackwalker;

import java.lang.StackWalker.Option;
import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;
import static java.lang.StackWalker.Option.SHOW_REFLECT_FRAMES;
import java.lang.StackWalker.StackFrame;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Stream;

public class StackWalking {
    public static void main(String[] args) {
        m1(Set.of());
        
        System.out.println();
        
        // Retain class references and show reflection frames
        m1(Set.of(RETAIN_CLASS_REFERENCE, 
                  SHOW_REFLECT_FRAMES));
    }

    public static void m1(Set<Option> options) {
        m2(options);
    }

    public static void m2(Set<Option> options) {
        // Call m3() using reflection
        try {
            System.out.println("Using StackWalker Options: " + 
                               options);
            StackWalking.class
                     .getMethod("m3", Set.class)
                     .invoke(null, options);
        } catch (NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | SecurityException e) {
            e.printStackTrace();
        }
    }

    public static void m3(Set<Option> options) {
        // Prints the call stack details
        StackWalker.getInstance(options)
                   .walk(StackWalking::processStack);
    }

    public static Void processStack(Stream<StackFrame> stack) {
        stack.forEach(frame -> {
            int bci = frame.getByteCodeIndex();
            String className = frame.getClassName();        
            Class<?> classRef = null;
            try {
                classRef = frame.getDeclaringClass();
            } catch (UnsupportedOperationException e) {
                // No action to take
            }
            
            String fileName = frame.getFileName();
            int lineNumber = frame.getLineNumber();
            String methodName = frame.getMethodName();
            boolean isNative = frame.isNativeMethod();
            
            StackTraceElement sfe = 
                frame.toStackTraceElement();
            
            System.out.printf("Native Method=%b", isNative);
            System.out.printf(", Byte Code Index=%d", bci);
            System.out.printf(", Module Name=%s", 
                              sfe.getModuleName());
            System.out.printf(", Module Version=%s", 
                              sfe.getModuleVersion());
            System.out.printf(", Class Name=%s", className);
            System.out.printf(", Class Reference=%s",
                              classRef);
            System.out.printf(", File Name=%s", fileName);
            System.out.printf(", Line Number=%d", lineNumber);
            System.out.printf(", Method Name=%s.%n", 
                              methodName);            
        });

        return null;
    }
}
