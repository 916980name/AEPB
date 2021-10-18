package com.example.AEPB.parklot;

public class Credential {
    private Car car;
    private UserToken token;

    private Credential() {}

    public Credential(Car car, TokenGenerator tokenGenerator) {
        if(car == null || tokenGenerator == null) {
            throw new IllegalArgumentException();
        }
        this.car = car;
        this.token = tokenGenerator.generateToken();
    }

    public Car getCar() {
        return car;
    }

    public UserToken getToken() {
        return token;
    }
}
