// Alphabet.java
package com.jdojo.misc;

public interface Alphabet {
    default boolean isAtOddPos(char c) {
        if (!Character.isLetter(c)) {
            throw new RuntimeException("Not a letter: " + c);
        }

        char uc = Character.toUpperCase(c);
        int pos = uc - 64;

        return pos % 2 == 1;
    }

    default boolean isAtEvenPos(char c) {
        if (!Character.isLetter(c)) {
            throw new RuntimeException("Not a letter: " + c);
        }

        char uc = Character.toUpperCase(c);
        int pos = uc - 64;

        return pos % 2 == 0;
    }
}
