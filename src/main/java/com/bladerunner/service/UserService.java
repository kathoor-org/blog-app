package com.bladerunner.service;

import java.util.List;

import com.bladerunner.entities.User;
import com.bladerunner.payloads.UserDto;

public interface UserService {

	UserDto saveUser(User user);

	UserDto updateUser(User user,Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUserById(Integer userId);
}
