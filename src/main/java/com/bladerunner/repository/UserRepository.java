package com.bladerunner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladerunner.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmailAddress(String email);

}
