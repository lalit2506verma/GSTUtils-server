package com.GSTUtils.server.Exception;

public class ReturnNotFoundException extends RuntimeException{

    public ReturnNotFoundException() {
        super("GSTIN not found");
    }

    public ReturnNotFoundException(String message) {
        super(message);
    }
}
