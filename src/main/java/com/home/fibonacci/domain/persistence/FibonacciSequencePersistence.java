package com.home.fibonacci.domain.persistence;

import com.home.fibonacci.domain.model.FibonacciSequence;

import java.util.List;

public interface FibonacciSequencePersistence {

    FibonacciSequence save(FibonacciSequence fibonacciSequence);

    FibonacciSequence getFibonacciSequenceByFibonacciSequenceNumber(
            Integer fibonacciSequenceNumber
    );

    List<FibonacciSequence> getTop3RequestedSequences();
}
