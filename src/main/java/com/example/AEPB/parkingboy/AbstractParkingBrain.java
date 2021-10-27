package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class AbstractParkingBrain implements Brain {
    private WeakReference<List<AbstractParkLot>> parkingLotList;

    private AbstractParkingBrain() {}

    public AbstractParkingBrain(List<AbstractParkLot> parkingLotList) {
        this.parkingLotList = new WeakReference<>(parkingLotList);
    }

    @Override
    public List<AbstractParkLot> getParkingLots() {
        return parkingLotList.get();
    }
}
