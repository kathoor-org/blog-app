package com.bladerunner.payloads;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

	private int postId;
	private String title;
	private String content;
	private String imgName;
	private Date addedDate;

	private CategoryDto categoryDto;
	private UserDto userDto;
	private Set<CommentDto> comments;
}
