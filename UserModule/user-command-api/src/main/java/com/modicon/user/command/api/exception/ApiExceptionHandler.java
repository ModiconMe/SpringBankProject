package com.modicon.user.command.api.exception;

import com.modicon.user.core.dto.BaseResponse;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<BaseResponse> handle(ApiException e) {
        return new ResponseEntity<>(new BaseResponse(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<BaseResponse> handle(CommandExecutionException e) {
        return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Spring validation exception handling
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handle(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new BaseResponse(e.getBindingResult().getFieldError().getDefaultMessage()), e.getStatusCode());
    }

}
