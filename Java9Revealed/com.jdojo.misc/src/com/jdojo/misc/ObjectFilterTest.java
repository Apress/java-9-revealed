// ObjectFilterTest.java
package com.jdojo.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputFilter.Config;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectFilterTest {
    public static void main(String[] args)  {         
        // Relative path of the output/input file
        File file = new File("serialized", "item.ser");
        
        // Make sure directories exist
        ensureParentDirExists(file);
        
        // Create an Item used in serialization and deserialization
        Item item = new Item(100, "Pen", new int[]{1,2,3,4});
                
        // Serialize the item
        serialize(file, item);

        // Print the global filter
        ObjectInputFilter globalFilter = Config.getSerialFilter();
        System.out.println("Global filter: " + globalFilter);
        
        // Deserialize the item
        Item item2 = deserialize(file);
        System.out.println("Deserialized using global filter: " + item2);
        
        // Use a filter to reject array size > 2
        String maxArrayFilterPattern = "maxarray=2";
        ObjectInputFilter maxArrayFilter = Config.createFilter(maxArrayFilterPattern);         
        Item item3 = deserialize(file, maxArrayFilter);
        System.out.println("Deserialized with a maxarray=2 filter: " + item3);
        
        // Create a custom filter
        ArrayLengthObjectFilter customFilter = new ArrayLengthObjectFilter(5);                
        Item item4 = deserialize(file, customFilter);
        System.out.println("Deserialized with a custom filter (maxarray=5): " + item4);
    }
    
    private static void serialize(File file, Item item) {        
        try (ObjectOutputStream out =  new ObjectOutputStream(new FileOutputStream(file))) {            
            out.writeObject(item);
            System.out.println("Serialized Item: " + item);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    private static Item deserialize(File file) {
        try (ObjectInputStream in =  new ObjectInputStream(new FileInputStream(file))) {                        
            Item item = (Item)in.readObject();
            return item;
        } catch (Exception e) {
            System.out.println("Could not deserialize item. Error: " + e.getMessage());
        } 
        
        return null;
    } 
    
    private static Item deserialize(File file, ObjectInputFilter filter) {
        try (ObjectInputStream in =  new ObjectInputStream(new FileInputStream(file))) {            
            // Set the object input filter passed in 
            in.setObjectInputFilter(filter);
            
            Item item = (Item)in.readObject();
            return item;
        } catch (Exception e) {
            System.out.println("Could not deserialize item. Error: " + e.getMessage());            
        } 
        
        return null;
    } 
    
    private static void ensureParentDirExists(File file) {
        File parent = file.getParentFile();
        if(!parent.exists()) {
            parent.mkdirs();
        } 
        
        System.out.println("Input/output file is " + file.getAbsolutePath());
    }
}
