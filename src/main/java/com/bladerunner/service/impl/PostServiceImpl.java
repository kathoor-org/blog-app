package com.bladerunner.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bladerunner.entities.Category;
import com.bladerunner.entities.Post;
import com.bladerunner.entities.User;
import com.bladerunner.exception.ResourceNotFoundException;
import com.bladerunner.payloads.CategoryDto;
import com.bladerunner.payloads.PostDto;
import com.bladerunner.payloads.PostResponse;
import com.bladerunner.payloads.UserDto;
import com.bladerunner.repository.CategoryRepo;
import com.bladerunner.repository.PostRepository;
import com.bladerunner.repository.UserRepository;
import com.bladerunner.service.PostService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		Post post = Post.builder().build();
		BeanUtils.copyProperties(postDto, post);
		post.setImgName("default.png");
		post.setAddedDate(new Date());
		post.setUser(userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)));
		post.setCategory(categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)));
		Post newPost = postRepo.save(post);

		return convertPostToPostDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {

		Post savedPost = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		savedPost.setAddedDate(postDto.getAddedDate());
		savedPost.setContent(postDto.getContent());
		savedPost.setImgName(postDto.getImgName());
		savedPost.setTitle(postDto.getTitle());

		postRepo.save(savedPost);

		return convertPostToPostDto(savedPost);
	}

	@Override
	public PostDto getPostById(int postId) {

		PostDto dto = null;

		dto = PostDto.builder().build();

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		return convertPostToPostDto(post);
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		List<PostDto> dtos = null;

		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> pagePosts = postRepo.findAll(pageable);
		List<Post> allPosts = pagePosts.getContent();

		dtos = allPosts.stream().map(post -> {
			return convertPostToPostDto(post);
		}).collect(Collectors.toList());

		PostResponse postResponse = PostResponse.builder().build();
		postResponse.setPosts(dtos);
		postResponse.setPageNo(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalElement(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public void deleteById(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		postRepo.delete(post);

	}

	@Override
	public List<PostDto> findByUser(int userId) {
		List<PostDto> postDtos = null;
		User user = null;

		user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		postDtos = postRepo.findByUser(user).stream().map(post -> {
			PostDto dto = PostDto.builder().build();
			return convertPostToPostDto(post);
		}).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> findByCategory(int categoryId) {
		List<PostDto> dtos = null;
		Category category = null;
		category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		dtos = postRepo.findByCategory(category).stream().map(post -> {

			PostDto dto = PostDto.builder().build();
			return convertPostToPostDto(post);
		}).collect(Collectors.toList());

		return dtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post> posts = postRepo.findByTitleContaining(keyword);

		return posts.stream().map(post -> {

			return convertPostToPostDto(post);
		}).collect(Collectors.toList());
	}

	private PostDto convertPostToPostDto(Post post) {
		PostDto postDto = PostDto.builder().build();
		BeanUtils.copyProperties(post, postDto);

		CategoryDto categoryDto = CategoryDto.builder().build();
		UserDto userDto = UserDto.builder().build();

		BeanUtils.copyProperties(post.getCategory(), categoryDto);
		BeanUtils.copyProperties(post.getUser(), userDto);

		postDto.setCategoryDto(categoryDto);
		postDto.setUserDto(userDto);
		return postDto;

	}

}
