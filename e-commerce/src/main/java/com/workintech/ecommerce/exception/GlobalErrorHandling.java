package com.workintech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandling {
@ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(ApiException apiException){
        ApiExceptionResponse apiExceptionResponse=new ApiExceptionResponse(apiException.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, apiException.getHttpStatus());
    }
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleException(MethodArgumentNotValidException exception){
    List errorList=exception.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> {
                Map<String,String> errorMap=new HashMap<>();
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                return errorMap;
            }).collect(Collectors.toList());
    return new ResponseEntity<>(errorList,HttpStatus.BAD_REQUEST);
}
    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(Exception exception){
        ApiExceptionResponse apiExceptionResponse=new ApiExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
