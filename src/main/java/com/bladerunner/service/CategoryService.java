package com.bladerunner.service;

import java.util.List;

import com.bladerunner.entities.Category;
import com.bladerunner.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(Category category);

	CategoryDto updateCategory(Category category, int id);

	void deleteCategoryById(int id);

	CategoryDto getCategoryById(int id);

	List<CategoryDto> getAllCategory();
}
