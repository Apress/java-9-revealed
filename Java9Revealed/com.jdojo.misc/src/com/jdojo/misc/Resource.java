// Resource.java
package com.jdojo.misc;

public class Resource implements AutoCloseable {    
    private final long id;
                    
    public Resource(long id) {        
        this.id = id;                
        System.out.printf("Created resource %d.%n", this.id);
    }
    
    public void useIt() {    
        System.out.printf("Using resource %d.%n", this.id);        
    }

    @Override
    public void close() {
        System.out.printf("Closing resource %d.%n", this.id);
    }
}