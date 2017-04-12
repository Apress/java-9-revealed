package com.jdojo.claim;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {    
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.jdojo.policy.Risk");
        Object obj = cls.getConstructor().newInstance();
        Field riskId = cls.getDeclaredField("riskId");
        
        Method getRiskId = cls.getMethod("getRiskId"); 
        
        riskId.setAccessible(true);
        
        
        System.out.println("Class: " + cls);
        System.out.println("Object: " + obj);
        System.out.println("riskId: " + riskId.get(obj));
        System.out.println("getRiskId: " + getRiskId.invoke(obj)); 
        
        
        
    }    
}
