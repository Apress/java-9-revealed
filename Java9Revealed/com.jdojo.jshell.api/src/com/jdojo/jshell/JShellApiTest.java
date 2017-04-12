// JShellApiTest.java
package com.jdojo.jshell.api;

import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;

public class JShellApiTest {
    public static void main(String[] args) {
        // Create an array of snippets to evaluate/execute
        // them sequentially
        String[] snippets = { "int x = 100;", 
                              "double x = 190.89;",
                              "long multiply(int value) {return value * multiplier;}",
                              "int multiplier = 2;",
                              "multiply(200)", 
                              "mul(99)"                                  
                            };

        try (JShell shell = JShell.create()) {            
            // Register a snippet event handler 
            shell.onSnippetEvent(JShellApiTest::snippetEventHandler);
            
            // Evaluate all snippets
            for(String snippet : snippets) {
                shell.eval(snippet);
                System.out.println("------------------------");
            }
        }
    }
 
    public static void snippetEventHandler(SnippetEvent se) {
        // Print the details of this snippet event
        Snippet snippet = se.snippet();
        System.out.printf("Snippet: %s%n", snippet.source());

        // Print the cause of this snippet event
        Snippet causeSnippet = se.causeSnippet();
        if (causeSnippet != null) {
            System.out.printf("Cause Snippet: %s%n", causeSnippet.source());
        }

        System.out.printf("Kind: %s%n", snippet.kind());
        System.out.printf("Sub-Kind: %s%n", snippet.subKind());
        System.out.printf("Previous Status: %s%n", se.previousStatus());
        System.out.printf("Current Status: %s%n", se.status());
        System.out.printf("Value: %s%n", se.value());

        Exception e = se.exception();
        if (e != null) {
            System.out.printf("Exception: %s%n", se.exception().getMessage());
        }
    }
}
