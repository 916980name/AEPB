package com.example.AEPB.parklot.simpleIDparklot;

import com.example.AEPB.parklot.ParkLotContext;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.TokenGenerator;
import com.example.AEPB.parklot.exception.TokenGeneratorInitException;

import java.util.UUID;

public class SimpleIDTokenGenerator implements TokenGenerator {
    private String id;

    public SimpleIDTokenGenerator() {
    }

    public SimpleIDTokenGenerator(ParkLotContext context) {
        this.setParkLotContext(context);
    }

    private void setId(ParkLotContext context) {
        this.id = context.getId();
    }

    @Override
    public Token generateToken() {
        if(id == null || id.isEmpty()) {
            throw new TokenGeneratorInitException();
        }
        return new UserParkIDToken(UUID.randomUUID().toString(), id);
    }

    @Override
    public void setParkLotContext(ParkLotContext context) {
        this.setId(context);
    }
}
