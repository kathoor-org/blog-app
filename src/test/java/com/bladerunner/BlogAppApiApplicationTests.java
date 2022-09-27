package com.bladerunner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bladerunner.repository.UserRepository;

@SpringBootTest
class BlogAppApiApplicationTests {

	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void printtRepo() {
		String repoClassName = repository.getClass().getName();
		String repoFQN = repository.getClass().getPackageName();
		System.out.println(repoClassName);
		System.out.println(repoFQN);
	}

}
