package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;
import com.example.AEPB.parklot.Car;
import com.example.AEPB.parklot.Credential;
import com.example.AEPB.parklot.ParkFunction;
import com.example.AEPB.parklot.ParkLotContext;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.exception.ParkLotFullException;
import com.example.AEPB.parklot.exception.RobotUnauthorizedException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class ParkingRobot extends AbstractParkingBrain {

    public ParkingRobot(List<AbstractParkLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Car getCar(Token token) {
        throw new RobotUnauthorizedException("Robot can not get Car");
    }

    @Override
    public Token park(Car car) {
        ParkFunction parkingLot = this.getParkingLots()
                .stream()
                .filter(parkLot -> parkLot.countEmptySlot() > 0)
                .max((parkLotA, parkLotB) -> {
                    MathContext mc = new MathContext(2, RoundingMode.CEILING);
                    BigDecimal emptyRatioA = new BigDecimal(parkLotA.countEmptySlot()).divide(new BigDecimal(parkLotA.countAllSlot()), mc);
                    BigDecimal emptyRatioB = new BigDecimal(parkLotB.countEmptySlot()).divide(new BigDecimal(parkLotB.countAllSlot()), mc);

                    if(emptyRatioA.compareTo(emptyRatioB) == 0) {
                        return Integer.parseInt(parkLotA.getId()) > Integer.parseInt(parkLotB.getId()) ? -1 : 1;
                    }

                    return emptyRatioA.compareTo(emptyRatioB);
                })
                .orElseThrow(ParkLotFullException::new);
        return parkingLot.park(car);
    }

    @Override
    public Optional<Credential> checkTokenExistence(Token token) {
        throw new RobotUnauthorizedException("Robot can not check token");
    }

    @Override
    public int countEmptySlot() {
        throw new RobotUnauthorizedException("Robot can not count empty slot");
    }
}
