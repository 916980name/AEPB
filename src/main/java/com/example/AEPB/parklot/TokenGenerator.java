package com.example.AEPB.parklot;


public interface TokenGenerator {
    Token generateToken();

    void setParkLotContext(ParkLotContext context);
}
