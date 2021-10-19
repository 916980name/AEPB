package com.example.AEPB.parklot;

import com.example.AEPB.parklot.exception.GenerateTokenCollisionException;
import com.example.AEPB.parklot.exception.InvalidUserTokenException;
import com.example.AEPB.parklot.exception.ParkLotFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleParkLot implements ParkLotFunction{

    private Map<Credential, Car> credentials;
    private Integer maxPlot;
    private TokenGenerator tokenGenerator;

    private SimpleParkLot() {}

    public SimpleParkLot(Integer maxPlot, TokenGenerator tokenGenerator) {
        if(maxPlot == null || maxPlot <= 0) {
            throw new IllegalArgumentException();
        }
        credentials = new HashMap<>(maxPlot);
        this.maxPlot = maxPlot;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public Car getCar(UserToken token) {
        if(token == null) {
            throw new IllegalArgumentException();
        }

        return credentials.remove(checkTokenExistence(token).orElseThrow(() -> {
            throw new InvalidUserTokenException();
        }));
    }

    @Override
    public UserToken park(Car car) {
        if(car == null) {
            throw new IllegalArgumentException();
        }
        if(credentials.size() >= maxPlot) {
            throw new ParkLotFullException();
        }
        Credential cr = new Credential(tokenGenerator);
        if(checkTokenExistence(cr.getToken()).isPresent()) {
            throw new GenerateTokenCollisionException();
        }

        credentials.put(cr, car);
        return cr.getToken();
    }

    @Override
    public Optional<Credential> checkTokenExistence(UserToken token) {
        return credentials.keySet().stream()
                .filter(credential ->
                        credential.getToken().equals(token))
                .findFirst();
    }
}
