package com.jdojo.services;

public class ModelExeption extends Exception {

    public ModelExeption() {
    }

    /**
     * Constructs an instance of <code>ModelExeption</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ModelExeption(String msg) {
        super(msg);
    }
}
