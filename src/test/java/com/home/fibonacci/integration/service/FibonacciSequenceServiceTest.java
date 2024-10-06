package com.home.fibonacci.integration.service;

import com.home.fibonacci.application.persistence.FibonacciSequencePersistenceImpl;
import com.home.fibonacci.application.service.FibonacciServiceImpl;
import com.home.fibonacci.domain.model.FibonacciSequence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FibonacciSequenceServiceTest {

    private static final Integer FBS_SEQUENCE_NUMBER_5 = 5;
    private static final Integer FBS_SUM_5 = 5;
    private static final boolean FBS_CALCULATE_SUM_1 = true;
    private static final Optional<Integer> FBS_OPT_1 = Optional.of(3);
    private static final Optional<Integer> FBS_OPT_2 = Optional.of(2);

    @InjectMocks
    private FibonacciServiceImpl fibonacciServiceImpl;

    @Mock
    private FibonacciSequencePersistenceImpl fibonacciSequencePersistenceImpl;

    @Test
    public void testCreate() {
        FibonacciSequence expectedFibonacciSequence = new FibonacciSequence(
                null,
                FBS_SEQUENCE_NUMBER_5,
                FBS_SUM_5);
        when(this.fibonacciSequencePersistenceImpl
                .save(expectedFibonacciSequence))
                .thenReturn(expectedFibonacciSequence);
        FibonacciSequence result = this.fibonacciServiceImpl
                .create(FBS_SEQUENCE_NUMBER_5,
                        FBS_CALCULATE_SUM_1,
                        FBS_OPT_1,
                        FBS_OPT_2
                );
        assertNotNull(result);
        assertEquals(expectedFibonacciSequence, result);
    }
}
