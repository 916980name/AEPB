package com.example.AEPB.parklot;

public class UserToken {
    private String token;

    private UserToken() {}

    public UserToken(String token) {
        if(token == null || token.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object object) {
        return this.token.equals(((UserToken) object).getToken());
    }
}
