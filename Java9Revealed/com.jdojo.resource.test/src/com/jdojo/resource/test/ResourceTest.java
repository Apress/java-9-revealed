// ResourceTest
package com.jdojo.resource.test;

import com.jdojo.exported.AppResource;
import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {
    public static void main(String[] args) {
        // A list of resources 
        String[] resources = {
            "java/lang/Object.class",
            "com/jdojo/resource/test/own.properties", 
            "com/jdojo/resource/test/ResourceTest.class",
            "unnamed.properties",
            "META-INF/invalid_pkg.properties",
            "com/jdojo/opened/opened.properties",
            "com/jdojo/exported/AppResource.class",
            "com/jdojo/resource/exported.properties",
            "com/jdojo/encapsulated/EncapsulatedTest.class",
            "com/jdojo/encapsulated/encapsulated.properties"
        };

        System.out.println("Using a Module:");
        Module otherModule = AppResource.class.getModule();
        for (String resource : resources) {
            lookupResource(otherModule, resource);
        }

        System.out.println("\nUsing a Class:");
        Class cls = ResourceTest.class;
        for (String resource : resources) {
            // Prepend a / to all resource names to make them absolute names
            lookupResource(cls, "/" + resource);
        }

        System.out.println("\nUsing the System ClassLoader:");
        ClassLoader clSystem = ClassLoader.getSystemClassLoader();
        for (String resource : resources) {
            lookupResource(clSystem, resource);
        }

        System.out.println("\nUsing the Platform ClassLoader:");
        ClassLoader clPlatform = ClassLoader.getPlatformClassLoader();
        for (String resource : resources) {
            lookupResource(clPlatform, resource);
        }
    }

    public static void lookupResource(Module m, String resource) {
        try {
            InputStream in = m.getResourceAsStream(resource);
            print(resource, in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void lookupResource(Class cls, String resource) {
        InputStream in = cls.getResourceAsStream(resource);
        print(resource, in);
    }

    public static void lookupResource(ClassLoader cl, String resource) {
        InputStream in = cl.getResourceAsStream(resource);
        print(resource, in);
    }

    private static void print(String resource, InputStream in) {
        if (in != null) {
            System.out.println("Found: " + resource);
        } else {
            System.out.println("Not Found: " + resource);
        }
    }
}
