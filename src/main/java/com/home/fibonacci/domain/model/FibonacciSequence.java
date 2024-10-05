package com.home.fibonacci.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class FibonacciSequence {

    private Long id;

    private Integer fibonacciSequenceNumber;

    private Integer sum;

    public static int fibonacciSum(
            int number,
            Optional<Integer> previousMinusOne,
            Optional<Integer> previousMinusTwo
    ) {
        if (previousMinusOne.isPresent() && previousMinusTwo.isPresent()) {
            return previousMinusOne.get() + previousMinusTwo.get();
        }

        int previous = 0, next = 1, sum;

        for (int i = 2; i < number; i++) {
            sum = previous + next;
            previous = next;
            next = sum;
        }

        return next;
    }
}
