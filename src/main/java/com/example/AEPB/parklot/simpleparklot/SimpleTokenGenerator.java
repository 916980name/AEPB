package com.example.AEPB.parklot.simpleparklot;

import com.example.AEPB.parklot.ParkLotContext;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.TokenGenerator;

import java.util.UUID;

public class SimpleTokenGenerator implements TokenGenerator {

    @Override
    public Token generateToken() {
        return new UserToken(UUID.randomUUID().toString());
    }

    @Override
    public void setParkLotContext(ParkLotContext context) {
        return;
    }
}
