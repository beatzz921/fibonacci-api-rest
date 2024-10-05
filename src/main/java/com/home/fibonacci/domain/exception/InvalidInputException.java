package com.home.fibonacci.domain.exception;

public class InvalidInputException extends ApplicationException {

    public InvalidInputException(final Object input) {
        super(ApplicationExceptionCode.INVALID_INPUT.getCode(),
                String.format("Input with value: '%s' is invalid", input));
    }
}