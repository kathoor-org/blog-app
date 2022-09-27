package com.bladerunner.payloads;

import com.bladerunner.entities.Post;
import com.bladerunner.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private int commentId;
	private String content;

}
