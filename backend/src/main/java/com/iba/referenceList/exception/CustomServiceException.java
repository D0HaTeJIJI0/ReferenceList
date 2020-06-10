package com.iba.referenceList.exception;

import lombok.Getter;

@Getter
public class CustomServiceException extends Exception {

    protected ErrorResponse errorResponse;

    public CustomServiceException(String message) {

    }
}
