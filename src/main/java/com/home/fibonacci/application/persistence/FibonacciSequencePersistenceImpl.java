package com.home.fibonacci.application.persistence;

import com.home.fibonacci.application.mapper.FibonacciSequenceEntityMapper;
import com.home.fibonacci.domain.model.FibonacciSequence;
import com.home.fibonacci.domain.persistence.FibonacciSequencePersistence;
import com.home.fibonacci.infrastructure.entity.FibonacciSequenceEntity;
import com.home.fibonacci.infrastructure.repository.FibonacciSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FibonacciSequencePersistenceImpl implements FibonacciSequencePersistence {

    @Autowired
    private FibonacciSequenceRepository fibonacciSequenceRepository;

    @Autowired
    private FibonacciSequenceEntityMapper fibonacciSequenceEntityMapper;

    public FibonacciSequence save(FibonacciSequence fibonacciSequence) {
        FibonacciSequenceEntity fibonacciSequenceEntity = this.fibonacciSequenceEntityMapper
                .asFibonacciSequenceEntity(fibonacciSequence);
        this.incrementRequestCounter(fibonacciSequenceEntity);

        return this.fibonacciSequenceEntityMapper
                .asFibonacciSequence(fibonacciSequenceEntity);
    }

    public FibonacciSequence getFibonacciSequenceByFibonacciSequenceNumber(
            Integer fibonacciSequenceNumber
    ) {
        FibonacciSequenceEntity fibonacciSequenceEntity = this.fibonacciSequenceRepository
                .findByFibonacciSequenceNumber(fibonacciSequenceNumber);

        if (fibonacciSequenceEntity != null) {
            this.incrementRequestCounter(fibonacciSequenceEntity);
        }

        return this.fibonacciSequenceEntityMapper
                .asFibonacciSequence(fibonacciSequenceEntity);
    }

    public List<FibonacciSequence> getTop3RequestedSequences() {
        Pageable pageable = PageRequest.of(0, 3);
        return this.fibonacciSequenceRepository.findTop3RequestedSequences(pageable)
                .stream()
                .map(this.fibonacciSequenceEntityMapper::asFibonacciSequence)
                .toList();
    }

    private void incrementRequestCounter(FibonacciSequenceEntity fibonacciSequenceEntity) {
        if (fibonacciSequenceEntity.getRequestCounter() != null) {
            fibonacciSequenceEntity.setRequestCounter(
                    fibonacciSequenceEntity.getRequestCounter() + 1
            );
        } else {
            fibonacciSequenceEntity.setRequestCounter(1);
        }

        this.fibonacciSequenceRepository.save(fibonacciSequenceEntity);
    }
}
