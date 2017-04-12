// Item.java
package com.jdojo.misc;

import java.io.Serializable;
import java.util.Arrays;

public class Item implements Serializable {
    private int id;    
    private String name;
    private int[] points;
        
    public Item(int id, String name, int[] points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }
    
    /* Add getters and setters here */
    
    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", points=" + Arrays.toString(points) + "]";
    }
}
