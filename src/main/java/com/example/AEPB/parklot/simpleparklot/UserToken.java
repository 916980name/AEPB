package com.example.AEPB.parklot.simpleparklot;

import com.example.AEPB.parklot.Token;

public class UserToken implements Token {
    private String token;

    private UserToken() {}

    public UserToken(String token) {
        if(token == null || token.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object object) {
        return this.token.equals(((UserToken) object).getToken());
    }
}
