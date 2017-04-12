// TestManifestAttributes.java
package com.jdojo.intruder;

import java.lang.reflect.Field;

public class TestManifestAttributes {
    public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {                
        Class<Long> cls = Long.class;
        Field svUid = cls.getDeclaredField("serialVersionUID");
        svUid.setAccessible(true);
        long svUidValue = (long)svUid.get(null);
        System.out.println("Long.serialVersionUID=" + svUidValue);        
    }
}
