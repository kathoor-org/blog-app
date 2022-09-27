package com.bladerunner.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bladerunner.entities.User;
import com.bladerunner.exception.ResourceNotFoundException;
import com.bladerunner.repository.UserRepository;

@Service
public class CustomUserDetailsService {

	@Autowired
	private UserRepository repository;

	public User loadUserByUsername(String username) {

		// loading user from database using username

		User user = repository.findByEmailAddress(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email :" + username, 0));

		return user;
	}

}
