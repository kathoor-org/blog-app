package com.bladerunner.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bladerunner.entities.User;
import com.bladerunner.payloads.ApiResponse;
import com.bladerunner.payloads.UserDto;
import com.bladerunner.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody User user) {

		UserDto UserDto = null;
		UserDto = userService.saveUser(user);
		return new ResponseEntity<UserDto>(UserDto, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {

		UserDto UserDto = null;
		UserDto = userService.getUserById(id);
		return new ResponseEntity<UserDto>(UserDto, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> users = null;
		users = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody User user, @PathVariable("id") int userId) {

		UserDto UserDto = null;
		UserDto = userService.updateUser(user, userId);
		return new ResponseEntity<UserDto>(UserDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") int userId) {

		userService.deleteUserById(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("resource deleted", true), HttpStatus.OK);
	}
}
