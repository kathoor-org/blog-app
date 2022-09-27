package com.bladerunner.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bladerunner.config.AppConstant;
import com.bladerunner.payloads.ApiResponse;
import com.bladerunner.payloads.ImageResponse;
import com.bladerunner.payloads.PostDto;
import com.bladerunner.payloads.PostResponse;
import com.bladerunner.service.FileService;
import com.bladerunner.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> postImageUpload(@RequestParam("image") MultipartFile file,
			@PathVariable("postId") int postId) throws IOException {

		PostDto dto = null;
		dto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, file);
		dto.setImgName(fileName);
		PostDto updatePost = postService.updatePost(dto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.ACCEPTED);

	}

	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int userId,
			@PathVariable("categoryId") int categoryId) {
		ResponseEntity<PostDto> entity = null;
		System.out.println("postDto: " + postDto);
		System.out.println("userId: " + userId);
		System.out.println("categoryId: " + categoryId);
		entity = new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
		return entity;
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable("postId") int postId) {
		PostDto dto = null;
		dto = postService.getPostById(postId);
		System.out.println(dto);
		return new ResponseEntity<PostDto>(dto, HttpStatus.FOUND);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = null;
		postResponse = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.FOUND);
	}

	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> findPostsByUser(@PathVariable("userId") int userId) {
		List<PostDto> dtos = null;
		dtos = postService.findByUser(userId);
		return new ResponseEntity<List<PostDto>>(dtos, HttpStatus.FOUND);
	}

	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> findPostsByCategory(@PathVariable("categoryId") int categoryId) {
		List<PostDto> dtos = null;
		dtos = postService.findByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(dtos, HttpStatus.FOUND);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("postId") int postId) {
		PostDto dto = null;
		dto = postService.updatePost(postDto, postId);
		System.out.println(dto);
		return new ResponseEntity<PostDto>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostByID(@PathVariable("postId") int postId) {
		postService.deleteById(postId);
		ApiResponse response = new ApiResponse("post deleted", true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/search/posts/{title}")
	public ResponseEntity<List<PostDto>> findByTitleContaining(@PathVariable("title") String keyword) {
		List<PostDto> dtos = postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(dtos, HttpStatus.FOUND);
	}

}
