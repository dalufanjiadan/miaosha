package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse<T> {

	private Integer code;
	private String message;
	private T data;

	@Getter
	@AllArgsConstructor
	enum Status {

		OK(0, "ok"), //
		BAD_REQUEST(1, "bad request"), //
		VALIDATION_EXCEPTION(2, "validation exception"), //
		EXCEPTION(3, "exception"), //
		DUPLICATE_ENTITY(4, "duplicate entity"), //
		// MAINTAIN(503, "维护中"), //
		UNAUTHORIZED(401, "401 unauthorized"), //
		ACCESS_DENIED(403, "403 access denied"), //
		NOT_FOUND(404, "404 not found"), //
		BAD_CREDENTIALS(499, "499 bad credentials. Invalid username/password supplied");

		private Integer code;
		private String message;
	}

	// @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd
	// HH:mm:ss")
	private LocalDateTime timestamp = LocalDateTime.now();

	public static <T> RestResponse<T> ok() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.OK);
		return response;
	}

	public static <T> RestResponse<T> ok(T data) {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.OK);
		response.setData(data);
		return response;
	}

	public static <T> RestResponse<T> badRequest() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.BAD_REQUEST);
		return response;
	}
	public static <T> RestResponse<T> badRequest(String message) {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.BAD_REQUEST);
		response.setMessage(message);
		return response;
	}

	public static <T> RestResponse<T> unauthorized() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.UNAUTHORIZED);
		return response;
	}

	public static <T> RestResponse<T> validationException(T data) {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.VALIDATION_EXCEPTION);
		response.setData(data);
		return response;
	}

	public static <T> RestResponse<T> badCredentials() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.BAD_CREDENTIALS);
		return response;
	}

	public static <T> RestResponse<T> accessDenied() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.ACCESS_DENIED);
		return response;
	}

	public static <T> RestResponse<T> exception() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.EXCEPTION);
		return response;
	}

	public static <T> RestResponse<T> notFound() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.NOT_FOUND);
		return response;
	}

	public static <T> RestResponse<T> duplicateEntity() {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(Status.DUPLICATE_ENTITY);
		return response;
	}

	private void setStatus(Status status) {
		this.code = status.getCode();
		this.message = status.getMessage();
	}

}