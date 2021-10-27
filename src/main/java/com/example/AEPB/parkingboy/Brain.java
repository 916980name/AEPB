package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;
import com.example.AEPB.parklot.ParkFunction;

import java.util.List;

public interface Brain extends ParkFunction {

    List<AbstractParkLot> getParkingLots();

}
