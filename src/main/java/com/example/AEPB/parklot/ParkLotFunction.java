package com.example.AEPB.parklot;

import java.util.Optional;

public interface ParkLotFunction {

    Car getCar(UserToken token);

    UserToken park(Car car);

    Optional<Credential> checkTokenExistence(UserToken token);
}
