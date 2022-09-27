package com.bladerunner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladerunner.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
