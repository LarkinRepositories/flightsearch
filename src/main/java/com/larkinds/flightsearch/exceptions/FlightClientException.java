package com.larkinds.flightsearch.exceptions;

import com.larkinds.flightsearch.dto.ValidationErrorDto;

import java.util.List;

public class FlightClientException extends RuntimeException {
    public FlightClientException(String message) {
        super(message);
    }

    public FlightClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FlightClientException(String message, List<ValidationErrorDto> errors) {
        super(message);
        this.validationErrorDtos = errors;
    }

    private List<ValidationErrorDto> validationErrorDtos;
}
