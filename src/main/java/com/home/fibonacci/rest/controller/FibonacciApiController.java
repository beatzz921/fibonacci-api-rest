package com.home.fibonacci.rest.controller;

import com.home.fibonacci.application.service.FibonacciServiceImpl;
import com.home.fibonacci.domain.model.FibonacciSequence;
import com.home.fibonacci.rest.mapper.FibonacciSequenceDTOMapper;
import com.home.fibonacci.rest.mapper.dto.FibonacciSequenceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/fibonacci-api")
@RestController
public class FibonacciApiController {

    private final FibonacciServiceImpl fibonacciServiceImpl;

    private final FibonacciSequenceDTOMapper fibonacciSequenceDTOMapper;

    public FibonacciApiController(
            FibonacciServiceImpl fibonacciServiceImpl,
            FibonacciSequenceDTOMapper fibonacciSequenceDTOMapper
    ) {
        this.fibonacciServiceImpl = fibonacciServiceImpl;
        this.fibonacciSequenceDTOMapper = fibonacciSequenceDTOMapper;
    }

    @GetMapping("/generate/{input}")
    public ResponseEntity<FibonacciSequenceDTO> generateSequence(
            @PathVariable("input") Integer number
    ) {
        FibonacciSequence fibonacciSequence = this.fibonacciServiceImpl.generate(number);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fibonacciSequenceDTOMapper.
                        asFibonacciSequenceDTO(fibonacciSequence)
                );
    }

    @GetMapping("/top_3_most_requested")
    public ResponseEntity<List<FibonacciSequenceDTO>> top3MostRequested() {
        List<FibonacciSequence> top3FibonacciSequences = this.fibonacciServiceImpl
                .getTop3MostRequestedSequences();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(top3FibonacciSequences
                        .stream()
                        .map(this.fibonacciSequenceDTOMapper::asFibonacciSequenceDTO)
                        .toList()
                );
    }
}
