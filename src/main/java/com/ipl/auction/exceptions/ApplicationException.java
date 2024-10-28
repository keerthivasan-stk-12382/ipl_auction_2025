package com.ipl.auction.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends Exception{

    long errorCode;
    String errorMessage;
    Exception innerException;


    public ApplicationException(long errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApplicationException(long errorCode, String errorMessage, Exception InnerException){
        super(errorMessage, InnerException);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.innerException = InnerException;
    }

    public String toString(){
        return "ErrorCode: " + errorCode + " ErrorMessage: " + getDetailedErrorMessage();
    }

    public String getDetailedErrorMessage() {
        if (innerException != null) {
            return "ErrorCode: " + errorCode + " ErrorMessage: " + errorMessage + " InnerException: " + innerException.getMessage();
        }
        return "ErrorCode: " + errorCode + " ErrorMessage: " + errorMessage;
    }
}
