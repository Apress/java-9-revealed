// QueryModule.java
package com.jdojo.module.api;

import java.sql.Driver;

public class QueryModule {
    public static void main(String[] args) throws Exception {
        Class<QueryModule> cls = QueryModule.class;
        Module m = cls.getModule();

        // Check if this module can read the java.sql module
        Module javaSqlModule = Driver.class.getModule();
        boolean canReadJavaSql = m.canRead(javaSqlModule);

        // Check if this module exports the com.jdojo.module.api package to all modules
        boolean exportsModuleApiPkg =  m.isExported("com.jdojo.module.api");

        // Check if this module exports the com.jdojo.module.api package to java.sql module
        boolean exportsModuleApiPkgToJavaSql = 
                m.isExported("com.jdojo.module.api", javaSqlModule);

        // Check if this module opens the com.jdojo.module.api package to java.sql module
        boolean openModuleApiPkgToJavaSql = m.isOpen("com.jdojo.module.api", javaSqlModule);
                
        // Print module type and name
        System.out.printf("Named Module: %b%n", m.isNamed());
        System.out.printf("Module Name: %s%n", m.getName());
        System.out.printf("Can read java.sql? %b%n", canReadJavaSql);
        System.out.printf("Exports com.jdojo.module.api? %b%n", exportsModuleApiPkg);
        System.out.printf("Exports com.jdojo.module.api to java.sql? %b%n", 
                exportsModuleApiPkgToJavaSql);
        System.out.printf("Opens com.jdojo.module.api to java.sql? %b%n", 
                openModuleApiPkgToJavaSql);
    }
}
