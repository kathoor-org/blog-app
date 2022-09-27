package com.bladerunner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bladerunner.payloads.ApiResponse;
import com.bladerunner.payloads.CommentDto;
import com.bladerunner.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService service;

	@PostMapping("/comments/{userId}/{postId}")
	public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto dto, @PathVariable("userId") int userId,
			@PathVariable("postId") int postId) {

		CommentDto savedDto = service.createComment(dto, userId, postId);
		return new ResponseEntity<CommentDto>(savedDto, HttpStatus.CREATED);

	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteCommentById(@PathVariable("commentId") int commentId) {
		service.deleteComment(commentId);
		ApiResponse response = new ApiResponse("deleted comment", true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
