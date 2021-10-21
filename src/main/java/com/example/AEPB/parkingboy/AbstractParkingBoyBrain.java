package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class AbstractParkingBoyBrain implements Brain {
    private WeakReference<List<AbstractParkLot>> parkingLotList;

    private AbstractParkingBoyBrain() {}

    public AbstractParkingBoyBrain(List<AbstractParkLot> parkingLotList) {
        this.parkingLotList = new WeakReference<>(parkingLotList);
    }

    @Override
    public List<AbstractParkLot> getParkingLots() {
        return parkingLotList.get();
    }
}
