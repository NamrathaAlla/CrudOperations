package com.mss.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> handleUserExp(UserException userException)
	{
	   return new ResponseEntity<String>("Employee not Existed",HttpStatus.NOT_FOUND);
	}

}
