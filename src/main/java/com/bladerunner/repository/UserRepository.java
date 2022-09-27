package com.bladerunner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladerunner.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
