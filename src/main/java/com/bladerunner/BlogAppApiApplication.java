package com.bladerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bladerunner.entities.User;
import com.bladerunner.exception.ResourceNotFoundException;
import com.bladerunner.repository.UserRepository;

@SpringBootApplication
public class BlogAppApiApplication {

	//@Autowired
	//private PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);

	}




}
