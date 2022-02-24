package com.bancow.process.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private T data;

    public static <T> ApiResponseDto of(T data) {
        return new ApiResponseDto(data);
    }
}
