package com.home.fibonacci.rest.handler;

import com.home.fibonacci.domain.exception.ApplicationExceptionCode;
import com.home.fibonacci.domain.exception.InvalidInputException;
import com.home.fibonacci.rest.mapper.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class FibonacciApiControllerHandler {

    private static final String MESSAGE = "{} code: {}, detail: {} ";

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputException(final InvalidInputException ex) {
        log.error(MESSAGE, "method='handleInvalidInputException'", ex.getCode(), ex.getMessage());
        return this.getErrorResponse(
                ApplicationExceptionCode.INVALID_INPUT,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    private ResponseEntity<Object> getErrorResponse(
            final ApplicationExceptionCode errorCode,
            final HttpStatus httpStatus,
            final String message
    ) {
        return new ResponseEntity<>(
                this.createErrorDTO(
                        errorCode,
                        message,
                        httpStatus
                ),
                httpStatus
        );
    }

    private ErrorDTO createErrorDTO(
            final ApplicationExceptionCode errorCode,
            final String message,
            final HttpStatus httpStatus
    ) {
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(String.valueOf(errorCode.getCode()));
        errorDTO.setTitle(httpStatus.toString());
        errorDTO.setDetail(message);
        return errorDTO;
    }
}
