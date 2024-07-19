package com.demo.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.dtos.ErrorDTO;
import com.demo.exceptions.ProductNotFoundException;

public class ControllerAdvice {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
		ErrorDTO errorDto = new ErrorDTO();
		errorDto.setMessage(productNotFoundException.getMessage());
		return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);	
	}

}
