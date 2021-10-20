package com.example.AEPB.parklot.simpleIDparklot;

import com.example.AEPB.parklot.Car;
import com.example.AEPB.parklot.Credential;
import com.example.AEPB.parklot.ParkLotContext;
import com.example.AEPB.parklot.Token;
import com.example.AEPB.parklot.TokenGenerator;
import com.example.AEPB.parklot.exception.InvalidUserTokenException;
import com.example.AEPB.parklot.exception.OutOfDateUserTokenException;
import com.example.AEPB.parklot.simpleparklot.SimpleParkLot;

import java.util.Optional;

public class SimpleIDParkLot extends SimpleParkLot implements ParkLotContext {
    private String id;

    @Override
    public String getId() {
        return this.id;
    }

    private SimpleIDParkLot(Integer maxPlot, TokenGenerator tokenGenerator) {
        super(maxPlot, tokenGenerator);
    }

    public SimpleIDParkLot(Integer maxPlot, TokenGenerator tokenGenerator, String id) {
        this(maxPlot, tokenGenerator);
        this.id = id;
        tokenGenerator.setParkLotContext(this);
    }

    @Override
    public Car getCar(Token token) {
        checkTokenValid(token);

        return super.getCar(token);
    }

    @Override
    public Token park(Car car) {
        return super.park(car);
    }

    @Override
    public Optional<Credential> checkTokenExistence(Token token) {
        checkTokenValid(token);

        return super.checkTokenExistence(token);
    }

    private void checkTokenValid(Token token) {
        if(!(token instanceof UserParkIDToken)) {
            throw new OutOfDateUserTokenException();
        }
        if(!this.id.equals(((UserParkIDToken)token).getParkLotId())) {
            throw new InvalidUserTokenException("Not my parking lot token");
        }
    }
}
