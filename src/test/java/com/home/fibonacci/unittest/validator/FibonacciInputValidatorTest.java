package com.home.fibonacci.unittest.validator;

import com.home.fibonacci.application.validator.FibonacciInputValidatorImpl;
import com.home.fibonacci.domain.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibonacciInputValidatorTest {

    private static final Integer FBS_SEQUENCE_NUMBER_VALID = 5;
    private static final Integer FBS_SEQUENCE_NUMBER_INVALID = -5;
    private final FibonacciInputValidatorImpl fibonacciInputValidatorImpl = new FibonacciInputValidatorImpl();

    @Test
    void testValidateInput_InvalidInput() {
        assertThrows(InvalidInputException.class, () -> this.fibonacciInputValidatorImpl
                .validateInput(FBS_SEQUENCE_NUMBER_INVALID));
    }

    @Test
    void testValidateInput_ValidInput() {
        assertDoesNotThrow(() -> this.fibonacciInputValidatorImpl
                .validateInput(FBS_SEQUENCE_NUMBER_VALID));
    }
}
