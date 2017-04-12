// AnnotationTest.java
package com.jdojo.module.api.annotation;

import java.lang.annotation.Annotation;

public class AnnotationTest {
    public static void main(String[] args) {
        // Get the module reference of the com.jdojo.module.api.annotation module
        Module m = AnnotationTest.class.getModule();
                
        // Print all annotations
        Annotation[] a = m.getAnnotations();
        for(Annotation ann : a) {
            System.out.println(ann);
        }
        
        // Read the Deprecated annotation
        Deprecated d = m.getAnnotation(Deprecated.class);
        if (d != null) {
            System.out.printf("Deprecated: since=%s, forRemoval=%b%n", 
                              d.since(), d.forRemoval());
        }
        
        // Read the Version annotation
        Version v = m.getAnnotation(Version.class);
        if (v != null) {
            System.out.printf("Version: major=%d, minor=%d%n", v.major(), v.minor());
        }
    }
}
