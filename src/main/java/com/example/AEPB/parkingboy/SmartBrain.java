package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;
import com.example.AEPB.parklot.Car;
import com.example.AEPB.parklot.ParkFunction;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.exception.ParkLotFullException;

import java.util.List;

public class SmartBrain extends SimpleBrain {
    public SmartBrain(List<AbstractParkLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Token park(Car car) {
        ParkFunction parkingLot = this.getParkingLots()
                .stream()
                .filter(parkLot -> parkLot.checkEmptySlot() > 0)
                .min((parkLotA, parkLotB) -> {
                    int i = -1 * (parkLotA.checkEmptySlot() - parkLotB.checkEmptySlot());
                    if (i != 0) {
                        return i;
                    }
                    return Integer.parseInt(parkLotA.getId()) > Integer.parseInt(parkLotB.getId()) ? 1 : -1;
                })
                .orElseThrow(ParkLotFullException::new);
        return parkingLot.park(car);
    }
}
