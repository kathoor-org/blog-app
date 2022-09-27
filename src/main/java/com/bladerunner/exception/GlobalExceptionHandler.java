package com.bladerunner.exception;

import com.bladerunner.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {

		Map<String, String> response = new HashMap<String, String>();
		ex.getFieldErrors().stream().map(error -> {
			response.put(error.getField(), error.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}
}
