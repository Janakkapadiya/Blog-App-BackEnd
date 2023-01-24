package com.blogapp.blog.application.controller;

import exception.DeleteApiResponse;
import exception.ErrorResponseHandle;
import exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFound.class})
    public ResponseEntity<ErrorResponseHandle> resourceNotFoundException(ResourceNotFound ex) {
        ErrorResponseHandle errorResponseHandle = new ErrorResponseHandle(400,ex.getMessage());
        errorResponseHandle.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponseHandle.setMessage("user not found");
        return new ResponseEntity<>(errorResponseHandle,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {DeleteApiResponse.class})
    public ResponseEntity<ErrorResponseHandle> idResourceNotFoundException(DeleteApiResponse ex) {
        ErrorResponseHandle errorResponseHandle = new ErrorResponseHandle(400,ex.getMessage());
        errorResponseHandle.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponseHandle.setMessage("user not found");
        return new ResponseEntity<>(errorResponseHandle,HttpStatus.NOT_FOUND);
    }
}
