package com.bladerunner.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ApiResponse {

	String message;
	boolean status;

	public ApiResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

}
