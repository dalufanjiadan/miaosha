package com.example.demo.exception;

import com.example.demo.model.RestResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected Object exception(Exception e) {

		return RestResponse.badRequest(e.getMessage());
	}
}