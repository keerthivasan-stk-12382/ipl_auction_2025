package com.ipl.auction.exceptions;

import java.util.HashMap;
import java.util.Map;

public class PlayerException extends ApplicationException{

    public static final long PLAYER_NOT_FOUND = 1001;
    public static final long PLAYER_ALREADY_EXISTS = 1002;

    public static final Map<Long, PlayerException> errorMessages = new HashMap<>(){
        {
            put(PLAYER_NOT_FOUND, new PlayerException(PLAYER_NOT_FOUND,"Player not found"));
            put(PLAYER_ALREADY_EXISTS, new PlayerException(PLAYER_ALREADY_EXISTS,"Player already exists"));
        }
    };


    public PlayerException(long errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public PlayerException(long errorCode, String errorMessage, Exception InnerException) {
        super(errorCode, errorMessage, InnerException);
    }

}
