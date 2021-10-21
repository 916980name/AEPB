package com.example.AEPB.parklot;

import com.example.AEPB.parkingboy.ParkingBoy;
import com.example.AEPB.parkingboy.SmartParkingBoy;
import com.example.AEPB.parklot.simpleIDparklot.UserParkIDToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    private static final int PARKING_LOT_COUNT = 10;
    private SmartParkingBoy parkingBoy;
    private List<AbstractParkLot> parkingLots = new ArrayList<>();

    @BeforeEach
    void init() {
        parkingLots.clear();
        for(int i = 0; i < PARKING_LOT_COUNT; i++) {
            parkingLots.add(ParkLotFactory.getSimpleIDParkLot(String.valueOf(i)));
        }
        parkingBoy = new SmartParkingBoy(parkingLots);
    }

    private void fillInParkingLot(int index, int number) {
        for(int i = 0; i < number; i++) {
            parkingLots.get(index).park(new Car(UUID.randomUUID().toString()));
        }
    }

    @Test
    void should_park_to_parklot_10_when_park_by_parking_boy_given_parklots_remain_slot_from_1_to_10() {
        Car car = new Car("1111");
        for(int i = 0; i < PARKING_LOT_COUNT; i++) {
            fillInParkingLot(i, 49 - i);
        }

        Token token = parkingBoy.park(car);

        assertFalse(token.getToken().isEmpty());
        assertEquals(9, parkingLots.get(9).checkEmptySlot());
    }

    @Test
    void should_park_to_parklot_5_when_park_by_parking_boy_given_parklots_remain_slot_is_4_5_6_7_10_3_10_2_1_10() {
        Car car = new Car("1111");
        fillInParkingLot(0, 50 - 4);
        fillInParkingLot(1, 50 - 5);
        fillInParkingLot(2, 50 - 6);
        fillInParkingLot(3, 50 - 7);
        fillInParkingLot(4, 50 - 10);
        fillInParkingLot(5, 50 - 3);
        fillInParkingLot(6, 50 - 10);
        fillInParkingLot(7, 50 - 2);
        fillInParkingLot(8, 50 - 1);
        fillInParkingLot(9, 50 - 10);

        Token token = parkingBoy.park(car);

        assertFalse(token.getToken().isEmpty());
        assertEquals(9, parkingLots.get(4).checkEmptySlot());
    }

    @Test
    void should_not_park_when_park_by_parking_boy_given_all_parkinglot_full() {
        Car car = new Car("1111");
        for(int i = 0; i < 10; i++) {
            fillInParkingLot(i, 50);
        }

        assertThrows(Exception.class, () -> parkingBoy.park(car));
    }

}
