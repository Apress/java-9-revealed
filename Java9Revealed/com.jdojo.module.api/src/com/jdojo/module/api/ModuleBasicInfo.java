// ModuleBasicInfo.java
package com.jdojo.module.api;

import com.jdojo.prime.PrimeChecker;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Exports;
import java.lang.module.ModuleDescriptor.Provides;
import java.lang.module.ModuleDescriptor.Requires;
import java.sql.Driver;
import java.util.Set;

public class ModuleBasicInfo {
    public static void main(String[] args) {
        // Get the module of the current class
        Class<ModuleBasicInfo> cls = ModuleBasicInfo.class;
        Module module = cls.getModule();              
        
        // Print module info
        printInfo(module);
        System.out.println("------------------");
        
        // Print module info
        printInfo(PrimeChecker.class.getModule());
        System.out.println("------------------");
        
        // Print module info
        printInfo(Driver.class.getModule());
    }

    public static void printInfo(Module m) {
        String moduleName = m.getName();
        boolean isNamed = m.isNamed();
        
        // Print module type and name
        System.out.printf("Module Name: %s%n", moduleName);
        System.out.printf("Named Module: %b%n", isNamed);
        
        // Get the module descriptor
        ModuleDescriptor desc = m.getDescriptor();

        // desc will be null for unnamed module
        if (desc == null) {
            Set<String> currentPackages = m.getPackages();
            System.out.printf("Packages: %s%n", currentPackages);
            return;
        }

        Set<Requires> requires = desc.requires();
        Set<Exports> exports = desc.exports();
        Set<String> uses = desc.uses();
        Set<Provides> provides = desc.provides();
        Set<String> packages = desc.packages();

        System.out.printf("Requires: %s%n", requires);
        System.out.printf("Exports: %s%n", exports);
        System.out.printf("Uses: %s%n", uses);
        System.out.printf("Provides: %s%n", provides);
        System.out.printf("Packages: %s%n", packages);
    }
}
