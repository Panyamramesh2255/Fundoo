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
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<ErrorResponse>registrationException(Exception e)
	{
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(401,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(NoteException.class)
	public ResponseEntity<ErrorResponse>noteException(Exception e)
	{
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(400,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(LabelException.class)
	public ResponseEntity<ErrorResponse>labelException(Exception e)
	{
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(400,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
