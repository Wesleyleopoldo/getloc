package com.getloc.syntech.getloc.helper;

import java.util.UUID;

import com.getloc.syntech.getloc.utils.Utils;

public class UserHelper extends Utils{

    public UserHelper(UUID uuid) {
        super();
    }

    public UserHelper setUuid(UUID newUuid) {
        uuid = newUuid;
        return this;
    }
    
    public UserHelper createRandomUUID() {
        uuid = UUID.randomUUID();
        return this;
    }
}