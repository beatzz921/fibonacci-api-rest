package com.home.fibonacci.application.mapper;


import com.home.fibonacci.domain.model.FibonacciSequence;
import com.home.fibonacci.infrastructure.entity.FibonacciSequenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FibonacciSequenceEntityMapper {

    FibonacciSequence asFibonacciSequence(
            FibonacciSequenceEntity fibonacciSequenceEntity
    );

    @Mapping(target = "requestCounter", ignore = true)
    FibonacciSequenceEntity asFibonacciSequenceEntity(
            FibonacciSequence fibonacciSequence
    );
}
