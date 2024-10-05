package com.home.fibonacci.application.validator;

import com.home.fibonacci.domain.exception.InvalidInputException;
import com.home.fibonacci.domain.validator.FibonacciInputValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class FibonacciInputValidatorImpl implements FibonacciInputValidator {

    public void validateInput(Integer input) {
        this.validatePositiveNumberInput(input)
                .orElseThrow(() -> new InvalidInputException(input));
    }

    private Optional<Integer> validatePositiveNumberInput(Integer input) {
        if (input >= 0) {
            return Optional.of(input);
        }

        return Optional.empty();
    }
}
