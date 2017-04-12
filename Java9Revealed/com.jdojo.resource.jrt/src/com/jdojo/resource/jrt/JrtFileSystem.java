// JrtFileSystem.java
package com.jdojo.resource.jrt;

import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class JrtFileSystem {
    public static void main(String[] args) throws IOException {
        // Create a jrt FileSystem
        FileSystem fs = FileSystems.getFileSystem(URI.create("jrt:/"));
        
        // Load an image from a module
        Path imagePath = fs.getPath("modules/java.desktop", "sun/print/resources/duplex.png");
        Image image = ImageIO.read(Files.newInputStream(imagePath));
        
        // Use the image object here
        System.out.println(image);
        
        // Read the Object.class file contents
        Path objectClassPath = fs.getPath("modules/java.base", "java/lang/Object.class");
        byte[] bytes = Files.readAllBytes(objectClassPath);
        System.out.println("Object.class file size: " + bytes.length);

        // List 5 packages in the runtime image
        Path packages = fs.getPath("packages");
        Files.walk(packages)
             .limit(5)
             .forEach(System.out::println);

        // List 5 modules’ entries in the runtime image
        Path modules = fs.getPath("modules");
        Files.walk(modules)
             .limit(5) 
             .forEach(System.out::println);        
    }
}
