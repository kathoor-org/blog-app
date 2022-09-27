package com.bladerunner.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bladerunner.entities.Category;
import com.bladerunner.payloads.ApiResponse;
import com.bladerunner.payloads.CategoryDto;
import com.bladerunner.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody Category category) {

		CategoryDto dto = null;
		dto = categoryService.createCategory(category);

		return new ResponseEntity<CategoryDto>(dto, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("id") int id) {
		CategoryDto dto = null;
		dto = categoryService.getCategoryById(id);

		return new ResponseEntity<CategoryDto>(dto, HttpStatus.FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> findAll() {
		List<CategoryDto> dtos = null;
		dtos = categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(dtos, HttpStatus.FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody Category category,
			@PathVariable("id") int id) {
		CategoryDto dto = null;
		dto = categoryService.updateCategory(category, id);
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") int id) {

		categoryService.deleteCategoryById(id);
		ApiResponse response = new ApiResponse("resource deleted", false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);
	}
}
