package com.bladerunner.service;

import java.util.List;

import com.bladerunner.entities.Category;
import com.bladerunner.entities.Post;
import com.bladerunner.entities.User;
import com.bladerunner.payloads.CategoryDto;
import com.bladerunner.payloads.PostDto;
import com.bladerunner.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId);

	PostDto updatePost(PostDto postDto, int postId);

	PostDto getPostById(int postId);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	void deleteById(int postId);

	List<PostDto> findByUser(int userId);

	List<PostDto> findByCategory(int categoryId);

	List<PostDto> searchPosts(String keyword);
}
