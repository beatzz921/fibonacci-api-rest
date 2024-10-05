package com.home.fibonacci.acceptance.controller;

import com.home.fibonacci.FibonacciApiApplication;
import com.home.fibonacci.rest.mapper.dto.FibonacciSequenceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FibonacciApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FibonacciApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String buildUrl(Integer number) {
        String host = "http://localhost:";
        String endpoint = "/fibonacci-api/generate";
        return host + this.port + endpoint + "/" + number;
    }

    private ResponseEntity<FibonacciSequenceDTO> consumeEndpoint(Integer fibonacciSequenceNumber) {
        String url = this.buildUrl(fibonacciSequenceNumber);
        return this.testRestTemplate.getForEntity(url, FibonacciSequenceDTO.class);
    }

    @Test
    void given_fibonacciSequenceNumber0_when_generate_then_callFibonacciSequenceService() throws Exception {

        Integer fibonacciSequenceNumber0 = 0;
        ResponseEntity<FibonacciSequenceDTO> response = this.consumeEndpoint(
                fibonacciSequenceNumber0
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getSum(), 0);
    }

    @Test
    void given_fibonacciSequenceNumber1_when_generate_then_callFibonacciSequenceService() throws Exception {

        Integer fibonacciSequenceNumber1 = 1;
        ResponseEntity<FibonacciSequenceDTO> response = this.consumeEndpoint(
                fibonacciSequenceNumber1
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getSum(), 1);
    }

    @Test
    void given_fibonacciSequenceNumber2_when_generate_then_callFibonacciSequenceService() throws Exception {

        Integer fibonacciSequenceNumber2 = 2;
        ResponseEntity<FibonacciSequenceDTO> response = this.consumeEndpoint(
                fibonacciSequenceNumber2
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getSum(), 1);
    }
}
