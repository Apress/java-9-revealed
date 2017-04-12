// AlphabetJdk9.java
package com.jdojo.misc;

public interface AlphabetJdk9 {
    default boolean isAtOddPos(char c) {
        return getPos(c) % 2 == 1;
    }

    default boolean isAtEvenPos(char c) {
        return getPos(c) % 2 == 0;
    }
    
    private int getPos(char c) {
        if (!Character.isLetter(c)) {
            throw new RuntimeException("Not a letter: " + c);
        }

        char uc = Character.toUpperCase(c);
        int pos = uc - 64;
        
        return pos;
    }
}