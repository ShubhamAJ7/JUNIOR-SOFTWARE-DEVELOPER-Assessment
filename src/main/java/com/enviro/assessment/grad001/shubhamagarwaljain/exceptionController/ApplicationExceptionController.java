package com.enviro.assessment.grad001.shubhamagarwaljain.exceptionController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enviro.assessment.grad001.shubhamagarwaljain.exception.InvestorNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.ProductNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.WithdrawalValidationException;

@RestControllerAdvice
public class ApplicationExceptionController {

//Managing Exceptions
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;

	}

	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvestorNotFoundException.class)
	public Map<String, String> InvestorNotFoundException(InvestorNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error Message :", ex.getMessage());
		return errorMap;

	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ProductNotFoundException.class)
	public Map<String, String> ProductNotFoundException(ProductNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error Message :", ex.getMessage());
		return errorMap;

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(WithdrawalValidationException.class)
	public Map<String, String> WithdrawalValidationException(WithdrawalValidationException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error Message :", ex.getMessage());
		return errorMap;

	}


}
