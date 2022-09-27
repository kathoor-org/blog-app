package com.bladerunner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladerunner.entities.Category;
import com.bladerunner.entities.Post;
import com.bladerunner.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
