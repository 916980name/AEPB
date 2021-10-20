package com.example.AEPB.parklot;

import com.example.AEPB.parklot.simpleIDparklot.SimpleIDParkLot;
import com.example.AEPB.parklot.simpleIDparklot.SimpleIDTokenGenerator;
import com.example.AEPB.parklot.simpleparklot.SimpleParkLot;
import com.example.AEPB.parklot.simpleparklot.SimpleTokenGenerator;

public class ParkLotFactory {
    public static final Integer SIMPLE_PARK_LOT_POSITION = 50;

    public static SimpleParkLot getSimpleParkLot() {
        return new SimpleParkLot(SIMPLE_PARK_LOT_POSITION, new SimpleTokenGenerator());
    }

    public static SimpleIDParkLot getSimpleIDParkLot(String id) {
        return new SimpleIDParkLot(SIMPLE_PARK_LOT_POSITION, new SimpleIDTokenGenerator(), id);
    }
}
