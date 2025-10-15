package com.user.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.constants.ErrorCode;
import com.user.dto.ApiError;
import com.user.exception.PasswordConfirmMismatchException;
import com.user.exception.PasswordNotMatchException;
import com.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	
	
    public final MessageSource messageSource;
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
		String message = messageSource.getMessage("error.user.notfound",null,null);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ErrorCode.USER_NOT_FOUND, message));
    }
	

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ApiError> handlePasswordNotMatch(PasswordNotMatchException ex) {
    	String message = messageSource.getMessage("error.password.incorrect",null,null);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(ErrorCode.PASSWORD_INCORRECT, message));
    }
    
    @ExceptionHandler(PasswordConfirmMismatchException.class)
    public ResponseEntity<ApiError> handlePasswordConfirmMismatch(PasswordConfirmMismatchException ex) {
    	String message = messageSource.getMessage("error.password.confirm.mismatch",null,null);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(ErrorCode.PASSWORD_NOT_MATCH, message));
    }
    
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
		String message = messageSource.getMessage("error.internal",null,null);
	    return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ApiError(ErrorCode.INTERNAL_SERVERERROR, message));
	}

}
