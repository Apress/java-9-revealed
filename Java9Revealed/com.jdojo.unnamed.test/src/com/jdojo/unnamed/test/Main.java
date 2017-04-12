// Main.java
package com.jdojo.unnamed.test;

import java.lang.reflect.Field;

public class Main {    
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.jdojo.reflect.Item");

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {            
            field.setAccessible(true);            
            System.out.println(field.getName() + " = " + field.get(null));
        }
    }    
}
