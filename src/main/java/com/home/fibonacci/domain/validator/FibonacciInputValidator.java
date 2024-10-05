package com.home.fibonacci.domain.validator;

import org.springframework.stereotype.Component;

@Component
public interface FibonacciInputValidator {

    void validateInput(Integer numberInput);
}
