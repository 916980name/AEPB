package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;
import com.example.AEPB.parklot.ParkFunction;
import com.example.AEPB.parklot.ParkLotContext;

import java.util.List;

public interface Brain extends ParkFunction {

    List<AbstractParkLot> getParkingLots();

    ParkLotContext getParkingLotContext(AbstractParkLot parkLot);
}
