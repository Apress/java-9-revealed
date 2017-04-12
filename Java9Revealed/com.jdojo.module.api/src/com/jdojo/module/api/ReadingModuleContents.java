// ReadingModuleContents.java
package com.jdojo.module.api;

import java.io.IOException;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;
import java.nio.ByteBuffer;
import java.util.Optional;

public class ReadingModuleContents {
    public static void main(String[] args) {        
        // Create a system module finder
        ModuleFinder finder = ModuleFinder.ofSystem();
        
        // The java.base module is guaranteed to exist
        Optional<ModuleReference> omr = finder.find("java.base");                
        ModuleReference moduleRef = omr.get();
        
        // Get a module reader and use it
        try (ModuleReader reader = moduleRef.open()) {
            // Read the Object class and print its size
            Optional<ByteBuffer> bb = reader.read("java/lang/Object.class");
            
            bb.ifPresent(buffer -> {
                System.out.println("Object.class Size: " + buffer.limit());
                
                // Release the byte buffer
                reader.release(buffer);
            }); 

            System.out.println("\nFive resources in the java.base module:");                 
            reader.list()
                  .limit(5)
                  .forEach(System.out::println);         
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
