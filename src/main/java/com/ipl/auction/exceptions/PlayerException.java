package com.ipl.auction.exceptions;


public class PlayerException extends ApplicationException{

    public static final PlayerException PLAYER_NOT_FOUND;
    public static final PlayerException PLAYER_ALREADY_EXISTS;

    static {
            PLAYER_NOT_FOUND = new PlayerException(ErrorCodes.PLAYER_NOT_FOUND,"Player not found");
            PLAYER_ALREADY_EXISTS = new PlayerException(ErrorCodes.PLAYER_ALREADY_EXISTS,"Player already exists");
    };

    public PlayerException(long errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public PlayerException(long errorCode, String errorMessage, Exception InnerException) {
        super(errorCode, errorMessage, InnerException);
    }

}
