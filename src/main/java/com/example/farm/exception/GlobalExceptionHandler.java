package com.example.farm.exception;

import com.example.farm.response.FDError;
import com.example.farm.response.FDResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FDException.class)
    public ResponseEntity<FDResponse<Object>> handleFDException(FDException ex) {
        logger.error("Handling FDException: ErrorCode - {}, ErrorType - {}, Message - {}", ex.getErrorCode(), ex.getErrorType(), ex.getErrorMessage());

        FDError error = new FDError(ex.getErrorCode(), ex.getErrorType(), ex.getErrorMessage());
        FDResponse<Object> response = new FDResponse<>();
        response.setStatus("ERROR");
        response.setError(error);

        HttpStatus status = ex.getErrorCode().equals("PRODUCT_NOT_FOUND") ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<FDResponse<Object>> handleNumberFormatException(NumberFormatException ex) {
        logger.error("Handling NumberFormatException: Invalid product ID format", ex);

        FDError error = new FDError("INVALID_ID_FORMAT", "BAD_REQUEST", "The product ID format is invalid.");
        FDResponse<Object> response = new FDResponse<>();
        response.setStatus("ERROR");
        response.setError(error);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FDResponse<Object>> handleGenericException(Exception ex) {
        logger.error("Handling Generic Exception", ex);

        FDError error = new FDError("INTERNAL_SERVER_ERROR", "SERVER_ERROR", "An unexpected error occurred.");
        FDResponse<Object> response = new FDResponse<>();
        response.setStatus("ERROR");
        response.setError(error);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
