package com.home.fibonacci.application.service;

import com.home.fibonacci.application.persistence.FibonacciSequencePersistenceImpl;
import com.home.fibonacci.application.validator.FibonacciInputValidatorImpl;
import com.home.fibonacci.domain.model.FibonacciSequence;
import com.home.fibonacci.domain.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    @Autowired
    private FibonacciInputValidatorImpl fibonacciInputValidatorImpl;

    @Autowired
    private FibonacciSequencePersistenceImpl fibonacciSequencePersistenceImpl;

    public List<FibonacciSequence> getTop3MostRequestedSequences() {
        return this.fibonacciSequencePersistenceImpl.getTop3RequestedSequences();
    }

    public FibonacciSequence generate(Integer input) {
        this.fibonacciInputValidatorImpl.validateInput(input);
        Integer minusOneNumber = input - 1;
        Integer minusTwoNumber = input - 2;
        FibonacciSequence fibonacciExists = this.getFibonacciSequenceByFibonacciSequenceNumber(input);

        if (fibonacciExists != null) {
            return fibonacciExists;
        } else if (input == 0 || input == 1) {
            return this.create(
                    input,
                    false,
                    Optional.empty(),
                    Optional.empty()
            );
        }

        FibonacciSequence fibonacciSequencePreviousMinusOneNumber = this.generate(minusOneNumber);
        FibonacciSequence fibonacciSequencePreviousMinusTwoNumber = this.generate(minusTwoNumber);

        return this.create(
                input,
                true,
                Optional.of(fibonacciSequencePreviousMinusOneNumber.getSum()),
                Optional.of(fibonacciSequencePreviousMinusTwoNumber.getSum())
        );
    }

    public FibonacciSequence getFibonacciSequenceByFibonacciSequenceNumber(
            Integer fibonacciSequenceNumber
    ) {
        return this.fibonacciSequencePersistenceImpl
                .getFibonacciSequenceByFibonacciSequenceNumber(fibonacciSequenceNumber);
    }

    private FibonacciSequence create(
            Integer number,
            boolean calculateSum,
            Optional<Integer> minusOneNumberFibonacci,
            Optional<Integer> minusTwoNumberFibonacci
    ) {

        int sum = number;

        if (calculateSum) {
            sum = FibonacciSequence.fibonacciSum(
                    number,
                    minusOneNumberFibonacci,
                    minusTwoNumberFibonacci
            );
        }

        FibonacciSequence fibonacciSequence = new FibonacciSequence();
        fibonacciSequence.setFibonacciSequenceNumber(number);
        fibonacciSequence.setSum(sum);

        return this.fibonacciSequencePersistenceImpl.save(fibonacciSequence);
    }
}
