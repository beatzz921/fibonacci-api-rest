package com.home.fibonacci.integration.persistence;

import com.home.fibonacci.application.mapper.FibonacciSequenceEntityMapper;
import com.home.fibonacci.application.persistence.FibonacciSequencePersistenceImpl;
import com.home.fibonacci.domain.model.FibonacciSequence;
import com.home.fibonacci.infrastructure.entity.FibonacciSequenceEntity;
import com.home.fibonacci.infrastructure.repository.FibonacciSequenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FibonacciSequencePersistenceTest {

    private static final Integer REQUEST_COUNTER = 11;
    private static final Long FBS_ID_1 = 1L;
    private static final Integer FBS_SEQUENCE_NUMBER_1 = 5;
    private static final Integer FBS_SUM_1 = 5;
    private static final Integer FBS_REQUEST_COUNTER_1 = 3;

    private static final Long FBS_ID_2 = 2L;
    private static final Integer FBS_SEQUENCE_NUMBER_2 = 3;
    private static final Integer FBS_SUM_2 = 2;
    private static final Integer FBS_REQUEST_COUNTER_2 = 8;

    private static final Long FBS_ID_3 = 3L;
    private static final Integer FBS_SEQUENCE_NUMBER_3 = 8;
    private static final Integer FBS_SUM_3 = 21;
    private static final Integer FBS_REQUEST_COUNTER_3 = 2;

    @InjectMocks
    private FibonacciSequencePersistenceImpl fibonacciSequencePersistenceImpl;

    @Mock
    private FibonacciSequenceRepository fibonacciSequenceRepository;

    @Mock
    private FibonacciSequenceEntityMapper fibonacciSequenceEntityMapper;

    @Test
    public void testGetTop3RequestedSequences() {

        List<FibonacciSequenceEntity> entities = this.fillData();
        when(this.fibonacciSequenceRepository
                .findTop3RequestedSequences(any(Pageable.class)))
                .thenReturn(entities);
        when(this.fibonacciSequenceEntityMapper
                .asFibonacciSequence(entities.getFirst()))
                .thenReturn(new FibonacciSequence(
                        FBS_ID_1,
                        FBS_SEQUENCE_NUMBER_1,
                        FBS_SUM_1)
                );
        when(this.fibonacciSequenceEntityMapper
                .asFibonacciSequence(entities.get(1)))
                .thenReturn(new FibonacciSequence(
                        FBS_ID_2,
                        FBS_SEQUENCE_NUMBER_2,
                        FBS_SUM_2)
                );
        when(this.fibonacciSequenceEntityMapper
                .asFibonacciSequence(entities.get(2)))
                .thenReturn(new FibonacciSequence(
                        FBS_ID_3,
                        FBS_SEQUENCE_NUMBER_3,
                        FBS_SUM_3)
                );

        List<FibonacciSequence> result = this.fibonacciSequencePersistenceImpl
                .getTop3RequestedSequences();

        assertEquals(3, result.size());
        assertEquals(2, result.get(1).getSum());
        assertEquals(5, result.get(0).getSum());
        assertEquals(21, result.get(2).getSum());
        verify(this.fibonacciSequenceRepository, times(1))
                .findTop3RequestedSequences(any(Pageable.class));
        verify(this.fibonacciSequenceEntityMapper, times(3))
                .asFibonacciSequence(any(FibonacciSequenceEntity.class));
    }

    @Test
    public void testGetFibonacciSequenceByFibonacciSequenceNumber() {
        FibonacciSequenceEntity expectedFibonacciSequenceEntity = new FibonacciSequenceEntity(
                FBS_ID_3,
                FBS_SEQUENCE_NUMBER_3,
                FBS_SUM_3,
                REQUEST_COUNTER);
        FibonacciSequence expectedFibonacciSequence = new FibonacciSequence(
                FBS_ID_3,
                FBS_SEQUENCE_NUMBER_3,
                FBS_SUM_3);
        when(this.fibonacciSequenceRepository
                .findByFibonacciSequenceNumber(FBS_SEQUENCE_NUMBER_3))
                .thenReturn(expectedFibonacciSequenceEntity);
        when(this.fibonacciSequenceEntityMapper
                .asFibonacciSequence(expectedFibonacciSequenceEntity))
                .thenReturn(expectedFibonacciSequence);
        FibonacciSequence result = this.fibonacciSequencePersistenceImpl
                .getFibonacciSequenceByFibonacciSequenceNumber(FBS_SEQUENCE_NUMBER_3);

        assertNotNull(result);
        assertEquals(
                expectedFibonacciSequence.getFibonacciSequenceNumber(),
                result.getFibonacciSequenceNumber()
        );
        assertEquals(expectedFibonacciSequence.getSum(), result.getSum());
        verify(this.fibonacciSequenceRepository)
                .findByFibonacciSequenceNumber(FBS_SEQUENCE_NUMBER_3);
        verify(this.fibonacciSequenceEntityMapper)
                .asFibonacciSequence(expectedFibonacciSequenceEntity);
    }

    private List<FibonacciSequenceEntity> fillData() {
        FibonacciSequenceEntity entity1 = new FibonacciSequenceEntity();
        entity1.setId(FBS_ID_1);
        entity1.setFibonacciSequenceNumber(FBS_SEQUENCE_NUMBER_1);
        entity1.setSum(FBS_SUM_1);
        entity1.setRequestCounter(FBS_REQUEST_COUNTER_1);
        FibonacciSequenceEntity entity2 = new FibonacciSequenceEntity();
        entity2.setId(FBS_ID_2);
        entity2.setFibonacciSequenceNumber(FBS_SEQUENCE_NUMBER_2);
        entity2.setSum(FBS_SUM_2);
        entity2.setRequestCounter(FBS_REQUEST_COUNTER_2);
        FibonacciSequenceEntity entity3 = new FibonacciSequenceEntity();
        entity3.setId(FBS_ID_3);
        entity3.setFibonacciSequenceNumber(FBS_SEQUENCE_NUMBER_3);
        entity3.setSum(FBS_SUM_3);
        entity3.setRequestCounter(FBS_REQUEST_COUNTER_3);

        return Arrays.asList(entity1, entity2, entity3);
    }
}
