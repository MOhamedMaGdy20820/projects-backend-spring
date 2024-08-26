package com.app.LMS.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private Boolean success;
//	private String message;
	private LocalDateTime dateTime;
	private List<String> details;

//	public ErrorResponse(String message, List<String> details) {
//		super();
//		this.message = message;
//		this.details = details;
//		this.success = Boolean.FALSE;
//		this.dateTime = LocalDateTime.now();
//	}


	public ErrorResponse( List<String> details) {
		this.success = Boolean.FALSE;
		this.dateTime = LocalDateTime.now();
		this.details = details;
	}
}
