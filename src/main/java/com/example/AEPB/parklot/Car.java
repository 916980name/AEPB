package com.example.AEPB.parklot;

public class Car {
    private String carNumber;

    private Car() {}

    public Car(String carNumber) {
        if(carNumber == null || carNumber.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }
}
