package com.example.AEPB.parklot.simpleIDparklot;

import com.example.AEPB.parklot.Token;

public class UserParkIDToken implements Token {
    private String parkLotId;
    private String token;

    private UserParkIDToken() {}

    public UserParkIDToken(String token, String parkLotId) {
        if(token == null || token.isEmpty()
                || parkLotId == null || parkLotId.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.parkLotId = parkLotId;
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public String getParkLotId() {
        return parkLotId;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof UserParkIDToken))
            return false;
        return this.token.equals(((Token) object).getToken())
                && this.parkLotId.equals(((UserParkIDToken) object).getParkLotId());
    }
}
