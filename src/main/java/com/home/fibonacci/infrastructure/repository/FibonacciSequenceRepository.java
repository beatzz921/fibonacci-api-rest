package com.home.fibonacci.infrastructure.repository;

import com.home.fibonacci.infrastructure.entity.FibonacciSequenceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FibonacciSequenceRepository extends JpaRepository<FibonacciSequenceEntity, Long> {

    FibonacciSequenceEntity findByFibonacciSequenceNumber(Integer number);

    @Query("SELECT fbs FROM FibonacciSequenceEntity fbs ORDER BY requestCounter DESC LIMIT 3")
    List<FibonacciSequenceEntity> findTop3RequestedSequences(Pageable pageable);
}
