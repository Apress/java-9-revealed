// LayerInfo.java
package com.jdojo.layer;

public class LayerInfo {
    private final static String VERSION = "1.0";
            
    static {
        System.out.println("Loading LayerInfo version " + VERSION);
    }
    
    public void printInfo() {        
        Class cls = this.getClass();
        ClassLoader loader = cls.getClassLoader();
        Module module = cls.getModule();
        String moduleName = module.getName();
        ModuleLayer layer = module.getLayer();
        
        System.out.println("Class Version: " + VERSION);
        System.out.println("Class Name: " + cls.getName());
        System.out.println("Class Loader: " + loader);
        System.out.println("Module Name: " + moduleName);
        System.out.println("Layer Name: " + layer);
    }    
}
