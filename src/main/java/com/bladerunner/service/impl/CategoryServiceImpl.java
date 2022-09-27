package com.bladerunner.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bladerunner.entities.Category;
import com.bladerunner.exception.ResourceNotFoundException;
import com.bladerunner.payloads.CategoryDto;
import com.bladerunner.repository.CategoryRepo;
import com.bladerunner.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	@Transactional(readOnly = false)
	public CategoryDto createCategory(Category category) {
		// validation
		CategoryDto dto = null;
		dto = CategoryDto.builder().build();

		Category savedCategory = categoryRepo.save(category);
		BeanUtils.copyProperties(savedCategory, dto);

		return dto;
	}

	@Override
	@Transactional(readOnly = false)
	public CategoryDto updateCategory(Category category, int id) {
		CategoryDto dto = null;
		dto = CategoryDto.builder().build();

		Category savedCategory = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		
		savedCategory.setCategoryDescription(category.getCategoryDescription());
		savedCategory.setCategoryTitle(category.getCategoryTitle());

		categoryRepo.save(savedCategory);
		BeanUtils.copyProperties(savedCategory, dto);

		return dto;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteCategoryById(int id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		categoryRepo.delete(category);
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDto getCategoryById(int id) {

		CategoryDto dto = null;
		dto = CategoryDto.builder().build();

		Category category = null;
		category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		BeanUtils.copyProperties(category, dto);

		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDto> getAllCategory() {

		List<CategoryDto> dtos = null;

		dtos = categoryRepo.findAll().stream().map(category -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(category, dto);
			return dto;
		}).toList();

		return dtos;
	}

}
