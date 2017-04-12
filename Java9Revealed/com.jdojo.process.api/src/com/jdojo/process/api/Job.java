// Job.java
package com.jdojo.process.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * An instance of this class is used as a job that sleeps at a 
 * regular interval up to a maximum duration. The sleep 
 * interval in seconds can be specified as the first argument 
 * and the sleep duration as the second argument while running.
 * this class. The default sleep interval and sleep duration 
 * are 5 seconds and 60 seconds, respectively. If these values 
 * are less than zero, zero is used instead.
 */
public class Job {
    // The job sleep interval
    public static final long DEFAULT_SLEEP_INTERVAL = 5;

    // The job sleep duration
    public static final long DEFAULT_SLEEP_DURATION = 60;

    public static void main(String[] args) {
        long sleepInterval = DEFAULT_SLEEP_INTERVAL;
        long sleepDuration = DEFAULT_SLEEP_DURATION;

        // Get the passed in sleep interval
        if (args.length >= 1) {
            sleepInterval = parseArg(args[0], 
                                    DEFAULT_SLEEP_INTERVAL);
            if (sleepInterval < 0) {
                sleepInterval = 0;
            }
        }

        // Get the passed in the sleep duration
        if (args.length >= 2) {
            sleepDuration = parseArg(args[1], 
                               DEFAULT_SLEEP_DURATION);
            if (sleepDuration < 0) {
                sleepDuration = 0;
            }
        }

        long pid = ProcessHandle.current().getPid();
        System.out.printf("Job (pid=%d) info: Sleep Interval" +         
                          "=%d seconds, Sleep Duration=%d " +  
                          "seconds.%n", 
                          pid, sleepInterval, sleepDuration);
        
        for (long sleptFor = 0; sleptFor < sleepDuration; 
                                sleptFor += sleepInterval) {
            try {
                System.out.printf("Job (pid=%d) is going to" + 
                                  " sleep for %d seconds.%n",
                                  pid, sleepInterval);
                
                // Sleep for the sleep interval
                TimeUnit.SECONDS.sleep(sleepInterval);
            } catch (InterruptedException ex) {
                System.out.printf("Job (pid=%d) was " + 
                                  "interrupted.%n", pid);
            }
        }        
    }
    
    /**
     * Starts a new JVM to run the Job class.       
     * @param sleepInterval The sleep interval when the Job 
     * class is run. It is passed to the JVM as the first 
     * argument.
     * @param sleepDuration The sleep duration for the Job 
     * class. It is passed to the JVM as the second argument.
     * @return The new process reference of the newly launched 
     * JVM or null if the JVM cannot be launched.
     */
    public static Process startProcess(long sleepInterval, 
                                       long sleepDuration) {
        // Store the command to launch a new JVM in a 
        // List<String>
        List<String> cmd = new ArrayList<>();
        
        // Add command components in order
        addJvmPath(cmd); 
        addModulePath(cmd);
        addClassPath(cmd);
        addMainClass(cmd);
        
        // Add arguments to run the class
        cmd.add(String.valueOf(sleepInterval));
        cmd.add(String.valueOf(sleepDuration));
        
        // Build the process attributes
        ProcessBuilder pb = new ProcessBuilder()
                                .command(cmd)
                                .inheritIO();
        
        String commandLine = pb.command()
                             .stream()
                             .collect(Collectors.joining(" "));
        System.out.println("Command used:\n" + commandLine);
        
        // Start the process
        Process p = null;
        try {
            p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return p;
    }
    
    /**
     * Used to parse the arguments passed to the JVM, which 
     * in turn is passed to the main() method.
     * @param valueStr The string value of the argument
     * @param defaultValue The default value of the argument if
     * the valueStr is not an integer.
     * @return valueStr as a long or the defaultValue if 
     * valueStr is not an integer.
     */
    private static long parseArg(String valueStr, 
                                 long defaultValue) {
        long value = defaultValue;
        if (valueStr != null) {
            try {
                value = Long.parseLong(valueStr);
            } catch (NumberFormatException e) {
                // no action needed
            }
        }
        return value;
    }
    
    /**
     * Adds the JVM path to the command list. It first attempts 
     * to use the command attribute of the current process; 
     * failing that it relies on the java.home system property.
     * @param cmd The command list
     */
    private static void addJvmPath(List<String> cmd) {
        // First try getting the command to run the current JVM
        String jvmPath = ProcessHandle.current()
                                      .info()
                                      .command().orElse("");
        if(jvmPath.length() > 0) {
            cmd.add(jvmPath);
        } else {
            // Try composing the JVM path using the java.home
            // system property
            final String FILE_SEPARATOR = 
                 System.getProperty("file.separator");

            jvmPath = System.getProperty("java.home") + 
                                    FILE_SEPARATOR +  "bin" + 
                                    FILE_SEPARATOR + "java";       
            cmd.add(jvmPath);
        }
    }
    
    /**
     * Adds a module path to the command list.
     * @param cmd The command list
     */
    private static void addModulePath(List<String> cmd) {         
        String modulePath = 
            System.getProperty("jdk.module.path");

        if(modulePath != null && 
           modulePath.trim().length() > 0) {
            cmd.add("--module-path");
            cmd.add(modulePath);
        }
    }
    
    /**
     * Adds class path to the command list.
     * @param cmd The command list
     */
    private static void addClassPath(List<String> cmd) {         
        String classPath = 
            System.getProperty("java.class.path");

        if(classPath != null && classPath.trim().length() > 0)
        {
            cmd.add("--class-path");
            cmd.add(classPath);
        }
    }
    
    /**
     * Adds a main class to the command list. Adds
     * module/className or just className depending on whether
     * the Job class was loaded in a named module or unnamed 
     * module
     * @param cmd The command list
     */
    private static void addMainClass(List<String> cmd) {         
        Class<Job> cls = Job.class;
        String className = cls.getName();
        Module module = cls.getModule();
        if(module.isNamed()) {
            String moduleName = module.getName();
            cmd.add("--module");
            cmd.add(moduleName + "/" + className);
        } else {            
            cmd.add(className);
        }
    }
}
