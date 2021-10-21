package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.AbstractParkLot;
import com.example.AEPB.parklot.Car;
import com.example.AEPB.parklot.Credential;
import com.example.AEPB.parklot.ParkFunction;
import com.example.AEPB.parklot.ParkLotContext;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.exception.InvalidUserTokenException;
import com.example.AEPB.parklot.exception.ParkLotFullException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SimpleBrain extends AbstractParkingBoyBrain {

    public SimpleBrain(List<AbstractParkLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkLotContext getParkingLotContext(AbstractParkLot parkLot) {
        return null;
    }

    @Override
    public Car getCar(Token token) {
        return this.getParkingLots()
                .stream()
                .map(parkFunction -> {
                    try {
                        return parkFunction.getCar(token);
                    } catch(Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(InvalidUserTokenException::new);
    }

    @Override
    public Token park(Car car) {
        ParkFunction parkingLot = this.getParkingLots()
                .stream()
                .filter(parkFunction -> parkFunction.checkEmptySlot() > 0)
                .findFirst()
                .orElseThrow(ParkLotFullException::new);
        return parkingLot.park(car);
    }

    @Override
    public Optional<Credential> checkTokenExistence(Token token) {
        return this.getParkingLots()
                .stream()
                .map(parkFunction -> parkFunction.checkTokenExistence(token))
                .findFirst()
                .orElseThrow(InvalidUserTokenException::new);
    }

    @Override
    public int checkEmptySlot() {
        return this.getParkingLots()
                .stream()
                .map(ParkFunction::checkEmptySlot)
                .reduce(0, Integer::sum);
    }
}
