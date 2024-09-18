package com.example.farm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FDException extends RuntimeException {
    private final String errorCode;
    private final String errorType;
    private final String errorMessage;

    public FDException(String errorCode, String errorType, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
