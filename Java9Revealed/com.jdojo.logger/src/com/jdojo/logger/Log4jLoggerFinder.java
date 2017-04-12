// Log4jLoggerFinder.java
package com.jdojo.logger;

import java.lang.System.LoggerFinder;

public class Log4jLoggerFinder extends LoggerFinder {
    // A logger to be used as a platform logger
    private final Log4jLogger logger = new Log4jLogger();

    @Override
    public System.Logger getLogger(String name, Module module) {        
        System.out.printf("Log4jLoggerFinder.getLogger(): " +
                          "[name=%s, module=%s]%n", name, module.getName());
        
        // Use the same logger for all modules        
        return logger;
    }
}
