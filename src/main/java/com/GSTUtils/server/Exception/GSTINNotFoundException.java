package com.GSTUtils.server.Exception;

public class GSTINNotFoundException extends RuntimeException{

    public GSTINNotFoundException() {
        super("GSTIN not found");
    }

    public GSTINNotFoundException(String message) {
        super(message);
    }
}
