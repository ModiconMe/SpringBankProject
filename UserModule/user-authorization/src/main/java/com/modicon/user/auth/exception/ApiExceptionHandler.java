package com.modicon.user.auth.exception;

import com.modicon.user.core.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<BaseResponse> handle(ApiException e) {
        return new ResponseEntity<>(new BaseResponse(e.getMessage()), e.getStatus());
    }

    /**
     * Spring validation exception handling
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handle(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new BaseResponse(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()), e.getStatusCode());
    }

}
