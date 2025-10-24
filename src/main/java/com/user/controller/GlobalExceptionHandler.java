package com.user.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.constants.ErrorCode;
import com.user.dto.ApiError;
import com.user.exception.FieldNotBlankException;
import com.user.exception.PasswordConfirmMismatchException;
import com.user.exception.PasswordNotMatchException;
import com.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	
    public final MessageSource messageSource;
	
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ApiError> handle(Exception ex) {
//	        ErrorMapping mapping = ERROR_MAP.getOrDefault(
//	            ex.getClass(),
//	            new ErrorMapping(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal", ErrorCode.INTERNAL_SERVERERROR)
//	        );
		 ErrorMapping mapping = ERROR_MAP.entrySet().stream()
			        .filter(e -> e.getKey().isInstance(ex))
			        .map(Map.Entry::getValue)
			        .findFirst()
			        .orElse(new ErrorMapping(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal", ErrorCode.INTERNAL_SERVERERROR));
		 System.out.println(ex.getMessage());
	        String message = messageSource.getMessage(mapping.messageKey(), null, Locale.getDefault());
	        return ResponseEntity.status(mapping.status()).body(new ApiError(mapping.code(), message));
	 }
	 
	 private static final Map<Class<? extends Exception>, ErrorMapping> ERROR_MAP = Map.of(
		        UserNotFoundException.class, new ErrorMapping(HttpStatus.NOT_FOUND, "error.user.notfound", ErrorCode.USER_NOT_FOUND),
		        PasswordNotMatchException.class, new ErrorMapping(HttpStatus.BAD_REQUEST, "error.password.incorrect", ErrorCode.PASSWORD_INCORRECT),
		        PasswordConfirmMismatchException.class, new ErrorMapping(HttpStatus.BAD_REQUEST, "error.password.confirm.mismatch", ErrorCode.PASSWORD_NOT_MATCH),
		        FieldNotBlankException.class, new ErrorMapping(HttpStatus.BAD_REQUEST, "error.field.blank", ErrorCode.FIELD_NOT_BLANK),
		        MethodArgumentNotValidException.class,new ErrorMapping(HttpStatus.BAD_REQUEST,"error.field.blank", ErrorCode.FIELD_NOT_BLANK)
	 );
	 private record ErrorMapping(HttpStatus status, String messageKey, String code) {}

}