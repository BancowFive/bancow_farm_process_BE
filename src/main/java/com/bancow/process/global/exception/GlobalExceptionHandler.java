package com.bancow.process.global.exception;

import com.bancow.process.domain.model.ErrorCode;
import com.bancow.process.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.ARGUMENT_NOT_VALID, e.getBindingResult());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<ErrorResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.NOT_SUPPORTED_HTTP_REQUEST_METHOD);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    protected ResponseEntity<ErrorResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.NOT_READABLE);
    }

    // 현재 Spring Security가 자체적으로 Excption 처리를 하고 있습니다.
    @ExceptionHandler(value = {RequestRejectedException.class})
    protected ResponseEntity<ErrorResponse> RequestRejectedException(RequestRejectedException e) {
        log.error("RequestRejectedException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.INCORRECT_PATH);
    }

}
