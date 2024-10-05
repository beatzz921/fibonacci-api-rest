package com.home.fibonacci.domain.service;

import com.home.fibonacci.domain.model.FibonacciSequence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FibonacciService {

    List<FibonacciSequence> getTop3MostRequestedSequences();

    FibonacciSequence generate(Integer number);

    FibonacciSequence getFibonacciSequenceByFibonacciSequenceNumber(
            Integer fibonacciSequenceNumber
    );
}
