package com.iba.referenceList.exception;

import org.springframework.http.HttpStatus;

public class DataDeleteException extends CustomServiceException {

    public DataDeleteException(String message) {
        super(message);
        errorResponse = new ErrorResponse(message, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }

}
