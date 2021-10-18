package com.example.AEPB.parklot;

import java.util.UUID;

public class SimpleTokenGenerator implements TokenGenerator{

    @Override
    public UserToken generateToken() {
        return new UserToken(UUID.randomUUID().toString());
    }
}
