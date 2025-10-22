package com.user.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
//	@ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
//		return buildResponse(HttpStatus.NOT_FOUND, "error.user.notfound", ErrorCode.USER_NOT_FOUND);
//    }
//	
//
//    @ExceptionHandler(PasswordNotMatchException.class)
//    public ResponseEntity<ApiError> handlePasswordNotMatch(PasswordNotMatchException ex) {
//    	String message = messageSource.getMessage("error.password.incorrect",null,null);
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(new ApiError(ErrorCode.PASSWORD_INCORRECT, message));
//    }
//    
//    @ExceptionHandler(PasswordConfirmMismatchException.class)
//    public ResponseEntity<ApiError> handlePasswordConfirmMismatch(PasswordConfirmMismatchException ex) {
//    	String message = messageSource.getMessage("error.password.confirm.mismatch",null,null);
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(new ApiError(ErrorCode.PASSWORD_NOT_MATCH, message));
//    }
//    
//    @ExceptionHandler(FieldNotBlankException.class)
//    public ResponseEntity<ApiError> handleFieldNotBlankException(FieldNotBlankException ex) {
//    	String message = messageSource.getMessage("error.field.blank",null,null);
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(new ApiError(ErrorCode.FIELD_NOT_BLANK, message));
//    }
//    
//	
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
//		String message = messageSource.getMessage("error.internal",null,null);
//	    return ResponseEntity
//	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
//	            .body(new ApiError(ErrorCode.INTERNAL_SERVERERROR, message));
//	}
//	
//	 private ResponseEntity<ApiError> buildResponse(HttpStatus status, String messageKey, String code) {
//	        String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
//	        return ResponseEntity.status(status).body(new ApiError(code, message));
//	 }
	 

	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ApiError> handle(Exception ex) {
	        ErrorMapping mapping = ERROR_MAP.getOrDefault(
	            ex.getClass(),
	            new ErrorMapping(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal", ErrorCode.INTERNAL_SERVERERROR)
	        );
	        String message = messageSource.getMessage(mapping.messageKey(), null, Locale.getDefault());
	        return ResponseEntity.status(mapping.status()).body(new ApiError(mapping.code(), message));
	 }
	 
	 private static final Map<Class<? extends Exception>, ErrorMapping> ERROR_MAP = Map.of(
		        UserNotFoundException.class, new ErrorMapping(HttpStatus.NOT_FOUND, "error.user.notfound", ErrorCode.USER_NOT_FOUND),
		        PasswordNotMatchException.class, new ErrorMapping(HttpStatus.BAD_REQUEST, "error.password.incorrect", ErrorCode.PASSWORD_INCORRECT),
		        PasswordConfirmMismatchException.class, new ErrorMapping(HttpStatus.BAD_REQUEST, "error.password.confirm.mismatch", ErrorCode.PASSWORD_NOT_MATCH),
		        FieldNotBlankException.class, new ErrorMapping(HttpStatus.BAD_REQUEST, "error.field.blank", ErrorCode.FIELD_NOT_BLANK)
	 );
	 private record ErrorMapping(HttpStatus status, String messageKey, String code) {}

}