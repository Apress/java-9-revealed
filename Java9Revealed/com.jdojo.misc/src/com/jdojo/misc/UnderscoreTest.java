/* Please uncoment the entire code to see the compile-time error in JDK 9 */

/*
// UnderscoreTest.java
package com.jdojo.misc;

public class UnderscoreTest {    
    public static void main(String[] args) {
        // Use an underscore as an identifier. It is a compile-time warning in JDK 8 and a
        // compile-time error in JDK 9.        
        int _ = 19;
        System.out.println(_);
        
        // Use an underscore in multi-character identifiers. They are fine in JDK 8 and JDK 9.
        final int FINGER_COUNT = 20;
        final String _prefix = "Sha";
    }
}
*/