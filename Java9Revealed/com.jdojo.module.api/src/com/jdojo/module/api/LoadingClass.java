// LoadingClass.java
package com.jdojo.module.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class LoadingClass {
    public static void main(String[] args) {
        loadClass("com.jdojo.intro.Welcome");
        loadClass("com.jdojo.intro.XYZ");

        String moduleName = "com.jdojo.intro";
        Optional<Module> m = ModuleLayer.boot().findModule(moduleName);
        if (m.isPresent()) {
            Module introModule = m.get();
            loadClass(introModule, "com.jdojo.intro.Welcome");
            loadClass(introModule, "com.jdojo.intro.ABC");
        } else {
            System.out.println("Module not found: " + moduleName + 
             ". Please make sure to add the module to the module path.");
        }
    }

    public static void loadClass(String className) {
        try {
            Class<?> cls = Class.forName(className);
            System.out.println("Class found: " + cls.getName());
            instantiateClass(cls);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);            
        }
    }

    public static void loadClass(Module m, String className) {
        Class<?> cls = Class.forName(m, className);
        if (cls == null) {
            System.out.println("Class not found: " + className);
        } else {
            System.out.println("Class found: " + cls.getName());
            instantiateClass(cls);
        }
    }

    public static void instantiateClass(Class<?> cls) {
        try {
            // Get the no-arg constructor
            Constructor<?> c = cls.getConstructor();
            Object o = c.newInstance();
            System.out.println("Instantiated class: " + cls.getName());
        } catch (InstantiationException | IllegalAccessException | 
                 IllegalArgumentException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchMethodException e) {
            System.out.println("No no-args constructor for class: " + cls.getName());
        }
    }
}
