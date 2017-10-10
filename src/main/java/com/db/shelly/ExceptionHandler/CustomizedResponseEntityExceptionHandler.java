package com.db.shelly.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.db.shelly.Exception.ExceptionResponse;
import com.db.shelly.Exception.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions( Exception ex , WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false) , ex.getMessage()) ;
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllExceptions( UserNotFoundException ex , WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false), ex.getMessage()) ;
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse,HttpStatus.NOT_FOUND);
		return responseEntity;
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getBindingResult().toString(), "valiation failed") ;
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
		return responseEntity;
		
	}

}
