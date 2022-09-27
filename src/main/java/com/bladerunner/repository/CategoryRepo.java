package com.bladerunner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladerunner.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
