package com.example.AEPB.parklot.exception;

public class InvalidUserTokenException extends RuntimeException {
    public InvalidUserTokenException() {
        super();
    }

    public InvalidUserTokenException(String message) {
        super(message);
    }
}
