package com.home.fibonacci.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "fibonacci_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciSequenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private Integer fibonacciSequenceNumber;

    private Integer sum;

    private Integer requestCounter;

}
