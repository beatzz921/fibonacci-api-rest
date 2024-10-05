package com.home.fibonacci.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionCode {

    INVALID_INPUT(0);

    private final int code;

}
