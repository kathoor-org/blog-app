package com.bladerunner.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class UserDto {

	private int id;
	private String name;
	private String password;
	private String emailAddress;
	private String about;
}
