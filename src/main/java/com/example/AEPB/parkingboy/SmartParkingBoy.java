package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;

import java.util.List;

public class SmartParkingBoy extends SmartBrain {
    public SmartParkingBoy(List<AbstractParkLot> parkingLotList) {
        super(parkingLotList);
    }
}
