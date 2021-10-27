package com.example.AEPB.parklot;


import java.util.Optional;

public interface ParkFunction {

    Car getCar(Token token);

    Token park(Car car);

    Optional<Credential> checkTokenExistence(Token token);

    int countEmptySlot();
}
