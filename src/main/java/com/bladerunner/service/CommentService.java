package com.bladerunner.service;

import com.bladerunner.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

	void deleteComment(Integer commentId);
}
