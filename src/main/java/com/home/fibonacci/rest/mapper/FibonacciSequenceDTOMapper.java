package com.home.fibonacci.rest.mapper;

import com.home.fibonacci.domain.model.FibonacciSequence;
import com.home.fibonacci.rest.mapper.dto.FibonacciSequenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FibonacciSequenceDTOMapper {

    @Mapping(source = "sum", target = "sum")
    FibonacciSequenceDTO asFibonacciSequenceDTO(FibonacciSequence fibonacciSequence);
}
