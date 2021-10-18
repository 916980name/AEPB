package com.example.AEPB.parklot;

import com.example.AEPB.parklot.exception.UserTokenCollisionException;

import java.util.HashMap;
import java.util.Map;

public class SimpleParkLot implements ParkLotFunction{
    private static final int MAX_TOKEN_COLLISION = 3;

    private Map<UserToken, Credential> credentials;
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
        if(!checkTokenExistence(token)) {
            return null;
        }
        Credential credential = credentials.remove(token);
        return credential.getCar();
    }

    @Override
    public UserToken park(Car car) {
        if(car == null) {
            throw new IllegalArgumentException();
        }
        if(credentials.size() >= maxPlot) {
            return null;
        }
        Credential cr = new Credential(car, tokenGenerator);
        int collision = 0;
        while(checkTokenExistence(cr.getToken())) {
            if(++collision >= MAX_TOKEN_COLLISION) {
                throw new UserTokenCollisionException();
            }
            cr = new Credential(car, tokenGenerator);
        }
        credentials.put(cr.getToken(), cr);
        return cr.getToken();
    }

    @Override
    public boolean checkTokenExistence(UserToken token) {
        return credentials.containsKey(token);
    }
}
