package com.example.demo.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.RestResponse;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	/**
	 * 将请求体解析并绑定到 java bean 时，如果出错，则抛出 MethodArgumentNotValidException 异常
	 *
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.OK)
	protected Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<Map<String, String>> errors = new ArrayList<>();
		for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
			Map<String, String> map = new HashMap<>();
			if (objectError instanceof FieldError) {
				FieldError fieldError = (FieldError) objectError;
				map.put("field", fieldError.getField());
				map.put("message", fieldError.getDefaultMessage());
			} else {
				map.put("field", objectError.getObjectName());
				map.put("message", objectError.getDefaultMessage());
			}
			errors.add(map);
		}

		return RestResponse.validationException(errors);
	}

	@ExceptionHandler(Exception.class)
	protected Object exception(Exception e) {

		log.error(e.getMessage());
		StackTraceElement[] stackTrace = e.getStackTrace();
		// for (int i = 0; i < stackTrace.length; i++) {
		// System.out.println(stackTrace[i].toString());
		// }

		return RestResponse.badRequest(e.getMessage());
	}
}