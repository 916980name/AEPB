package com.example.AEPB.parklot;

import com.example.AEPB.parklot.simpleparklot.SimpleParkLot;
import com.example.AEPB.parklot.simpleparklot.UserToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleParkLotTest {
    private SimpleParkLot parkLot;

    private Car getACar() {
        return new Car("atestcarnumber");
    }

    @BeforeEach
    public void setup() {
        parkLot = ParkLotFactory.getSimpleParkLot();
    }

    @Test
    void should_throw_exception_when_recording_car_given_empty_car_number() {
        assertThrows(Exception.class, () -> new Car(null));
    }

    @Test
    void should_throw_exception_when_parking_car_given_null() {
        assertThrows(Exception.class, () -> parkLot.park(null));
    }

    @Test
    void should_throw_exception_when_parking_car_given_full_parking_lot() {
        // given
        // can not get and mock private variables
//        when(parkLot.getCredentials().size()).thenReturn(ParkLotFactory.SIMPLE_PARK_LOT_POSITION);
        for(int i = 0; i < ParkLotFactory.SIMPLE_PARK_LOT_POSITION; i++) {
            parkLot.park(new Car(String.valueOf(i)));
        }

        // when
        assertThrows(Exception.class, () -> parkLot.park(getACar()));

    }

    @Test
    void should_return_token_when_parking_car_given_parking_lot_available() {
        Token token = parkLot.park(getACar());

        assertNotNull(token);
        assertNotNull(token.getToken());
    }

    @Test
    void should_throw_exception_when_get_car_given_null_token() {
        parkLot.park(getACar());

        assertThrows(Exception.class, () -> parkLot.getCar(null));
    }

    @Test
    void should_throw_exception_when_get_car_given_token_not_exist() {
        parkLot.park(getACar());

        assertThrows(Exception.class, () -> parkLot.getCar(new UserToken("obviouseNotExistToken")));
    }

    @Test
    void should_return_car_when_get_car_given_exist_token() {
        Token token = parkLot.park(getACar());

        Car car = parkLot.getCar(token);

        assertEquals("atestcarnumber", car.getCarNumber());
    }
}

