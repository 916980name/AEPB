package com.example.AEPB.parklot;

public interface ParkLotFunction {

    Car getCar(UserToken token);

    UserToken park(Car car);

    boolean checkTokenExistence(UserToken token);
}
