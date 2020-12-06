package com.example.demo.exception;

import com.example.demo.model.RestResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected Object exception(Exception e) {

		log.error(e.getMessage());
		StackTraceElement[] stackTrace = e.getStackTrace();
		for (int i = 0; i < stackTrace.length; i++) {
			System.out.println(stackTrace[i].toString());
		}

		return RestResponse.badRequest(e.getMessage());
	}
}