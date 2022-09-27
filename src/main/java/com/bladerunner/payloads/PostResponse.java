package com.bladerunner.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PostResponse {

	private List<PostDto> posts;
	private int pageNo;
	private int pageSize;
	private long totalElement;
	private int totalPages;
	private boolean lastPage;

}
