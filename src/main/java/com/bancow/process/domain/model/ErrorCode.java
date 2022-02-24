package com.bancow.process.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 100번대 에러

    // 200번대 에러

    // 300번대 에러

    // 400번대 에러
    FARM_NOT_FOUND(NOT_FOUND, "D001"),
    NOT_SUPPORTED_HTTP_REQUEST_METHOD(METHOD_NOT_ALLOWED, "D002"),
    NOT_READABLE(BAD_REQUEST, "D003"),
    ARGUMENT_NOT_VALID(BAD_REQUEST, "D004"),
    INCORRECT_PATH(NOT_FOUND, "D005");

    // 500번대 에러

    private final HttpStatus httpStatus;
    private final String code;
}
