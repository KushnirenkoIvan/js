package com.mc.kushnirenko;

public class IllegalContentTypeException extends Exception {

    public IllegalContentTypeException(String connType) {
        super(connType);
    }

}
