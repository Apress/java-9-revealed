// UpdateModule.java
package com.jdojo.module.api;

import java.util.ServiceLoader;

public class UpdateModule {
    public static <T> T findFirstService(Class<T> service) {
        /* Before loading the service providers, check if this module can use (or load) the
           service. If not, update the module to use the service.
        */
        Module m = UpdateModule.class.getModule();
        if (!m.canUse(service)) {
            m.addUses(service);
        }
                
        return ServiceLoader.load(service)
                            .findFirst()
                            .orElseThrow(
         () -> new RuntimeException("No service provider found for the service: " + 
                                    service.getName()));         
    }
}
