// FindingModule.java
package com.jdojo.module.api;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

public class FindingModule {
    public static void main(String[] args) {
        // Create module paths
        Path mp1 = Paths.get("C:\\Java9Revealed\\lib");
        Path mp2 = Paths.get("C:\\Java9Revealed\\customLib");
        
        // Create a module finder
        ModuleFinder finder = ModuleFinder.of(mp1, mp2);
        
        // Find all modules that this finder can locate
        Set<ModuleReference> moduleRefs = finder.findAll();
        
        // Print the details of the modules found
        moduleRefs.forEach(FindingModule::printInfo);    
    }
    
    public static void printInfo(ModuleReference mr) {
        ModuleDescriptor md = mr.descriptor();        
        Optional<URI> location = mr.location();
        URI uri = null;
        if(location.isPresent()) {
            uri = location.get();
        }
        
        System.out.printf("Module: %s, Location: %s%n", md.name(), uri);       
    }
}
