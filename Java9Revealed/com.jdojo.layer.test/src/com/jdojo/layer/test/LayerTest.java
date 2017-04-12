// LayerTest.java
package com.jdojo.layer.test;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class LayerTest {
    public static void main(String[] args) {
        /* Location for the custom module. You will need to change the 
           path to point to a directory on your PC that contains the 
           modular JAR for the com.jdojo.layer (version 2.0) module.
         */
        final String CUSTOM_MODULE_LOCATION = "C:\\Java9Revealed\\customLib";

        // Define the set of root modules to be resolved in the custom layer
        Set<String> rootModules = Set.of("com.jdojo.layer");

        // Create a custom layer
        ModuleLayer customLayer = createLayer(CUSTOM_MODULE_LOCATION, rootModules);

        // Test the class in the boot layer
        ModuleLayer bootLayer = ModuleLayer.boot();
        testLayer(bootLayer);
        System.out.println();

        // Test the class in the custom layer
        testLayer(customLayer);
    }

    public static ModuleLayer createLayer(String modulePath, Set<String> rootModules) {
        Path path = Paths.get(modulePath);

        // Define the module finders to be used in creating a 
        // configuration for the custom layer
        ModuleFinder beforFinder = ModuleFinder.of(path);
        ModuleFinder afterFinder = ModuleFinder.of();

        // Create a configuration for the custom layer
        Configuration parentConfig = ModuleLayer.boot().configuration();
        Configuration config = 
                parentConfig.resolve(beforFinder, afterFinder, rootModules);

        /* Create a custom layer with one class loader. The parent for 
           the class loader is the system class loader. The boot layer is 
           the parent layer of this custom layer. 
         */
        ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
        ModuleLayer parentLayer = ModuleLayer.boot();
        ModuleLayer layer = parentLayer.defineModulesWithOneLoader(config,                 sysClassLoader);

        // Check if we loaded the module in this layer
        if (layer.modules().isEmpty()) {
            System.out.println("\nCould not find the module " + rootModules
                    + " at " + modulePath + ". "
                    + "Please make sure that the com.jdojo.layer.v2.jar exists "
                    + "at this location." + "\n");
        }

        return layer;
    }

    public static void testLayer(ModuleLayer layer) {
        final String moduleName = "com.jdojo.layer";
        final String className = "com.jdojo.layer.LayerInfo";
        final String methodName = "printInfo";

        try {
            // Load the class
            Class<?> cls = layer.findLoader(moduleName)
                                .loadClass(className);

            // Instantiate the class using its no-args constructor
            Object obj = cls.getConstructor().newInstance();

            // Find the method
            Method method = cls.getMethod(methodName);

            // Call the method that will print the details
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
