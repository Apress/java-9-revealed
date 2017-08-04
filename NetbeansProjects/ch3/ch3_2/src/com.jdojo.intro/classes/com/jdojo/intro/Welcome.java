package com.jdojo.intro;

public class Welcome {

    public static final void main(String[] args) {
        System.out.println("Welcome to the Module System.");
        // Print the module name of the Welcome class
        final Class<Welcome> cls = Welcome.class;
        final Module mod = cls.getModule();
        final String moduleName = mod.getName();
        System.out.format("Module Name: %s%n", moduleName);
    }

}
