package com.example.AEPB.parklot;


public class Credential {
    private Token token;

    private Credential() {}

    public Credential(TokenGenerator tokenGenerator) {
        if(tokenGenerator == null) {
            throw new IllegalArgumentException();
        }
        this.token = tokenGenerator.generateToken();
    }

    public Token getToken() {
        return token;
    }
}
