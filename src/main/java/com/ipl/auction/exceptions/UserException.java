package com.ipl.auction.exceptions;

import com.ipl.auction.entity.User;

public class UserException extends ApplicationException{

    //User specific Exceptions
    public static final UserException USER_NOT_FOUND;
    public static final UserException USER_ALREADY_EXISTS;
    public static final UserException USER_NOT_AUTHORIZED;
    public static final UserException INCORRECT_PASSWORD;

    //General Exception
    public static final UserException SUPER_ADMIN_ALREADY_EXISTS ;

    static {
        USER_NOT_FOUND = new UserException(ErrorCodes.USER_NOT_FOUND, "User '%s' not found");
        USER_ALREADY_EXISTS = new UserException(ErrorCodes.USER_ALREADY_EXISTS, "User '%s' already exists");
        USER_NOT_AUTHORIZED = new UserException(ErrorCodes.USER_NOT_AUTHORIZED, "User '%s' not authorized");
        INCORRECT_PASSWORD = new UserException(ErrorCodes.INCORRECT_PASSWORD, "Incorrect Password for User '%s'");

        SUPER_ADMIN_ALREADY_EXISTS = new UserException(ErrorCodes.SUPER_ADMIN_ALREADY_EXISTS, "Super Admin already exists");
    }


    private User user;

    public UserException(long errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public UserException setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String getErrorMessage() {
        if(user != null){
            return String.format(super.getErrorMessage(), user.getUsername());
        }
        return super.getErrorMessage();
    }



}
