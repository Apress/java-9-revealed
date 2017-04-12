// ResourceTest.java
package com.jdojo.resource.prejdk9;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ResourceTest {
    public static void main(String[] args) {
        System.out.println("Finding resources using the system class loader:");
        findSystemResource("java/lang/Object.class");
        findSystemResource("com/jdojo/resource/prejdk9/ResourceTest.class");
        findSystemResource("com/jdojo/prime/PrimeChecker.class");
        findSystemResource("sun/print/resources/duplex.png");

        System.out.println("\nFinding resources using the Class class:");

        // A relative resource name - Will not find Object.class
        findClassResource("java/lang/Object.class");

        // An absolute resource name - Will find Object.class
        findClassResource("/java/lang/Object.class");

        // A relative resource name - will find the class 
        findClassResource("ResourceTest.class");

        // Load the wordtonumber.properties file
        loadProperties("/wordtonumber.properties");

        // Will not find the properties because we are using
        // an absolute resource name
        loadProperties("/resources/numbertoword.properties");

        // Will find the properties
        loadProperties("resources/numbertoword.properties");
    }

    public static void findSystemResource(String resource) {
        URL url = ClassLoader.getSystemResource(resource);
        System.out.println(url);
    }

    public static URL findClassResource(String resource) {
        URL url = ResourceTest.class.getResource(resource);
        System.out.println(url);
        return url;
    }

    public static Properties loadProperties(String resource) {
        Properties p1 = new Properties();
        URL url = ResourceTest.class.getResource(resource);
        if (url == null) {
            System.out.println("Properties not found: " + resource);
            return p1;
        }

        try {
            p1.load(url.openStream());
            System.out.println("Loaded properties from " + resource);
            System.out.println(p1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return p1;
    }
}
