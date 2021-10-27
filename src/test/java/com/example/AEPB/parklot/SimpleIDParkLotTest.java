package com.example.AEPB.parklot;

import com.example.AEPB.parkingboy.ParkingBoy;
import com.example.AEPB.parklot.simpleIDparklot.UserParkIDToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleIDParkLotTest {
    private ParkingBoy parkingBoy;
    private List<AbstractParkLot> parkingLots = new ArrayList<>();

    @BeforeEach
    void init() {
        parkingLots.clear();
        for(int i = 0; i < 10; i++) {
            parkingLots.add(ParkLotFactory.getSimpleIDParkLot(String.valueOf(i)));
        }
        parkingBoy = new ParkingBoy(parkingLots);
    }

    private void fillInParkingLot(int index, int number) {
        for(int i = 0; i< number; i++) {
            parkingLots.get(index).park(new Car(UUID.randomUUID().toString()));
        }
    }

    @Test
    void should_get_token_when_park_by_parking_boy_given_empty_parking_lots() {
        Car car = new Car("1111");

        Token token = parkingBoy.park(car);

        assertFalse(token.getToken().isEmpty());
    }

    @Test
    void should_park_to_parklot_1_when_park_by_parking_boy_given_parklot_1_remain_slot_and_parklot_2_full() {
        Car car = new Car("1111");
        fillInParkingLot(0, 45);
        fillInParkingLot(1, 50);

        Token token = parkingBoy.park(car);

        assertFalse(token.getToken().isEmpty());
        assertEquals(4, parkingLots.get(0).countEmptySlot());
    }

    @Test
    void should_not_park_when_park_by_parking_boy_given_all_parkinglot_full() {
        Car car = new Car("1111");
        for(int i = 0; i < 10; i++) {
            fillInParkingLot(i, 50);
        }

        assertThrows(Exception.class, () -> parkingBoy.park(car));
    }

    @Test
    void should_get_car_when_get_car_by_parking_boy_given_parking_by_parking_boy() {
        Car car = new Car("1111");
        Token token = parkingBoy.park(car);

        Car getCar = parkingBoy.getCar(token);

        assertEquals("1111", getCar.getCarNumber());
    }

    @Test
    void should_get_car_when_get_car_by_parking_boy_given_parking_by_yourself() {
        Car car = new Car("1111");
        Token token = parkingLots.get(5).park(car);

        Car getCar = parkingBoy.getCar(token);

        assertEquals("1111", getCar.getCarNumber());
    }

    @Test
    void should_get_car_when_get_car_by_yourself_given_parking_by_parking_boy() {
        Car car = new Car("1111");
        Token token = parkingBoy.park(car);

        Car getCar = parkingLots.get(Integer.parseInt(((UserParkIDToken) token).getParkLotId())).getCar(token);

        assertEquals("1111", getCar.getCarNumber());
    }
}
