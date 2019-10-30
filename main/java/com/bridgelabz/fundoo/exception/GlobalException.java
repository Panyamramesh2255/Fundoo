package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorResponse>loginException(Exception e)
	{
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(401,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
