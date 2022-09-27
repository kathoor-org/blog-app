package com.bladerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bladerunner.repository.UserRepository;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("xyz"));
	}

}
