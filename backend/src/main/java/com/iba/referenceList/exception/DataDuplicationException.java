package com.iba.referenceList.exception;

import org.springframework.http.HttpStatus;

public class DataDuplicationException extends CustomServiceException {

    public DataDuplicationException(String message) {
        super(message);
        errorResponse = new ErrorResponse(message, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }

}
