// ImportDeprecationWarning.java
package com.jdojo.deprecation;

import java.io.StringBufferInputStream;

public class ImportDeprecationWarning {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        StringBufferInputStream sbis = 
                new StringBufferInputStream("Hello");

        for(int c = sbis.read(); c != -1; c = sbis.read()) {
            System.out.println((char)c);
        }
    }
}
