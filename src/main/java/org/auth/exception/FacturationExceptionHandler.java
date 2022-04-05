package org.auth.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FacturationExceptionHandler extends ResponseEntityExceptionHandler {
	
	 @ExceptionHandler(value = {NotFoundException.class})
     public ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
		 String errorMessageDescription = ex.getLocalizedMessage();
		 if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
	        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	 }
	
	 @ExceptionHandler(value = {ForbiddenException.class})
     public ResponseEntity<Object> handleForbiddenException(RuntimeException ex, WebRequest request) {
		 String errorMessageDescription = ex.getLocalizedMessage();
		 if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
	        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
	 }
	
	 @ExceptionHandler(value = {BadRequestException.class})
     public ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
		 String errorMessageDescription = ex.getLocalizedMessage();
		 if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
	        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	 }
	 	 
}
