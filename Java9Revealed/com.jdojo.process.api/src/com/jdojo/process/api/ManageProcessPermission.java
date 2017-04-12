// ManageProcessPermission.java
package com.jdojo.process.api;

import java.util.concurrent.ExecutionException;

public class ManageProcessPermission {    
    public static void main(String[] args) {
        // Get the process count
        long count = ProcessHandle.allProcesses().count();
        System.out.printf("Process Count: %d%n", count);
        
        // Start a new process
        Process p = Job.startProcess(1, 3);

        try {
            p.toHandle().onExit().get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        
        // Install a security manager
        SecurityManager sm = System.getSecurityManager();
        if(sm == null) {
            System.setSecurityManager(new SecurityManager());
            System.out.println(
               "A security manager is installed.");
        }
        
        // Get the process count
        try {
            count = ProcessHandle.allProcesses().count();
            System.out.printf("Process Count: %d%n", count);
        } catch(RuntimeException e) {
            System.out.println("Could not get a " + 
                          "process count: " + e.getMessage());
        }
        
        // Start a new process
        try {
            p = Job.startProcess(1, 3);
            p.toHandle().onExit().get();
        } catch (InterruptedException | ExecutionException | 
                 RuntimeException e) {
            System.out.println("Could not start a new " + 
                               "process: " + e.getMessage());
        }
    }
}
