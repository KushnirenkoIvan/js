package com.mc.kushnirenko.task;

public class IllegalContentTypeException extends Exception {

    public IllegalContentTypeException(String connType) {
        super(connType);
    }

}
