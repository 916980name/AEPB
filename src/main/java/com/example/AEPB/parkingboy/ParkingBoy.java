package com.example.AEPB.parkingboy;

import com.example.AEPB.parklot.Car;
import com.example.AEPB.parklot.Credential;
import com.example.AEPB.parklot.ParkFunction;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.exception.InvalidUserTokenException;
import com.example.AEPB.parklot.exception.ParkLotFullException;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ParkingBoy implements ParkFunction{
    private WeakReference<List<ParkFunction>> parkingLotList;

    private ParkingBoy() {}

    public ParkingBoy(List<ParkFunction> parkFunctionList) {
        this.parkingLotList = new WeakReference<>(parkFunctionList);
    }

    @Override
    public Car getCar(Token token) {
        return parkingLotList.get()
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
        ParkFunction parkingLot = parkingLotList.get()
                .stream()
                .filter(parkFunction -> parkFunction.checkEmptySlot() > 0)
                .findFirst()
                .orElseThrow(ParkLotFullException::new);
        return parkingLot.park(car);
    }

    @Override
    public Optional<Credential> checkTokenExistence(Token token) {
        return parkingLotList.get()
                .stream()
                .map(parkFunction -> parkFunction.checkTokenExistence(token))
                .findFirst()
                .orElseThrow(InvalidUserTokenException::new);
    }

    @Override
    public int checkEmptySlot() {
        return parkingLotList.get()
                .stream()
                .map(ParkFunction::checkEmptySlot)
                .reduce(0, Integer::sum);
    }
}
