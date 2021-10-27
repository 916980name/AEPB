package com.example.AEPB.parklot;

import com.example.AEPB.parkingboy.ParkingRobot;
import com.example.AEPB.parkingboy.SmartParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingRobotTest {
    private static final int[] PARKING_LOT_COUNT_ARRAY = {10, 20, 30, 30, 20, 10};
    private ParkingRobot parkingRobot;
    private List<AbstractParkLot> parkingLots = new ArrayList<>();

    @BeforeEach
    void init() {
        parkingLots.clear();
        for (int i = 0; i < PARKING_LOT_COUNT_ARRAY.length; i++) {
            parkingLots.add(ParkLotFactory.getSimpleIDParkLotWithParkingSlot(String.valueOf(i), PARKING_LOT_COUNT_ARRAY[i]));
        }
        parkingRobot = new ParkingRobot(parkingLots);
    }

    private void fillInParkingLot(int index, int number) {
        for (int i = 0; i < number; i++) {
            parkingLots.get(index).park(new Car(UUID.randomUUID().toString()));
        }
    }

    @Test
    void should_park_to_parklot_3_when_park_by_parking_robot_given_all_parklots_occupied_slot_10() {
        Car car = new Car("1111");
        for (int i = 0; i < PARKING_LOT_COUNT_ARRAY.length; i++) {
            fillInParkingLot(i, 10);
        }

        Token token = parkingRobot.park(car);

        assertFalse(token.getToken().isEmpty());
        assertEquals(19, parkingLots.get(2).countEmptySlot());
    }

    @Test
    void should_park_to_parklot_1_when_park_by_parking_robot_given_all_parklots_remain_slot_10() {
        Car car = new Car("1111");
        for (int i = 0; i < PARKING_LOT_COUNT_ARRAY.length; i++) {
            fillInParkingLot(i, PARKING_LOT_COUNT_ARRAY[i] - 10);
        }

        Token token = parkingRobot.park(car);

        assertFalse(token.getToken().isEmpty());
        assertEquals(9, parkingLots.get(0).countEmptySlot());
    }

}