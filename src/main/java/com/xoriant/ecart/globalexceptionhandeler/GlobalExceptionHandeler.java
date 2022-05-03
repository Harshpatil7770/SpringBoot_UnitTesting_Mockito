package com.xoriant.ecart.globalexceptionhandeler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandeler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserInputException.class)
	public ResponseEntity<String> userInputExceptionHandeler(UserInputException expetion) {
		return new ResponseEntity<String>("Please Enter the fileds", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementExceptionHandeler.class)
	public ResponseEntity<String> noSuchElementException(NoSuchElementExceptionHandeler exception) {
		return new ResponseEntity<String>("Element Not Found", HttpStatus.OK);

	}
}
