package com.bladerunner.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bladerunner.entities.Comment;
import com.bladerunner.entities.Post;
import com.bladerunner.entities.User;
import com.bladerunner.exception.ResourceNotFoundException;
import com.bladerunner.payloads.CommentDto;
import com.bladerunner.repository.CommentRepo;
import com.bladerunner.repository.PostRepository;
import com.bladerunner.repository.UserRepository;
import com.bladerunner.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Comment comment = Comment.builder().build();
		BeanUtils.copyProperties(commentDto, comment);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = commentRepo.save(comment);
		BeanUtils.copyProperties(savedComment, commentDto);

		return commentDto;
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		commentRepo.delete(comment);
	}

}
