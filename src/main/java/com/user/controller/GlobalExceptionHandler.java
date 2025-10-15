package com.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.constants.ErrorCode;
import com.user.dto.ApiError;
import com.user.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ErrorCode.USER_NOT_FOUND, ex.getMessage()));
    }
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
	    return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ApiError(ErrorCode.INTERNAL_SERVERERROR, ex.getMessage()));
	}

}
