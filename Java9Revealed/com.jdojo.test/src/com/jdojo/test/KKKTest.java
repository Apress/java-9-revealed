// KKKTest.java
package com.jdojo.test;



public class KKKTest {
    public static void main(String[] args)  {       
       ModuleLayer layer = ModuleLayer.boot();
       for(Module m : layer.modules()) {
           ClassLoader cl = m.getClassLoader();
           String loader = "Bootstrap";
           if (cl != null) {
               loader = cl.getName();               
           }
           
           System.out.println(loader + "\t" + m.getName());
       }
    }
}
