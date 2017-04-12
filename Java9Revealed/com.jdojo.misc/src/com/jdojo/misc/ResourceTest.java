// ResourceTest.java
package com.jdojo.misc;

public class ResourceTest {
     public static void main(String[] args) {
         Resource r1 = new Resource(1);
         Resource r2 = new Resource(2);
         
         try(r1; r2) {
             r1.useIt();
             r2.useIt();
             r2.useIt();
         } 
         
         useResource(new Resource(3));
     }
     
     public static void useResource(Resource res) {
         try(res; Resource res4 = new Resource(4)) {
             res.useIt();
             res4.useIt();
         } 
     }
}
