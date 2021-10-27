package com.example.AEPB.parklot.exception;

public class RobotUnauthorizedException extends RuntimeException{
    public RobotUnauthorizedException(String message) {
        super(message);
    }
}
