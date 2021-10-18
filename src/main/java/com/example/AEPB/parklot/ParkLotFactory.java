package com.example.AEPB.parklot;

public class ParkLotFactory {
    public static final Integer SIMPLE_PARK_LOT_POSITION = 50;

    public static SimpleParkLot getSimpleParkLot() {
        return new SimpleParkLot(SIMPLE_PARK_LOT_POSITION, new SimpleTokenGenerator());
    }
}
