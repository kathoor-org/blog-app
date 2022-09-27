package com.bladerunner.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bladerunner.entities.User;
import com.bladerunner.exception.ResourceNotFoundException;
import com.bladerunner.payloads.UserDto;
import com.bladerunner.repository.UserRepository;
import com.bladerunner.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional(readOnly = false)
	public UserDto saveUser(User user) {
        // validation
		
		UserDto dto = UserDto.builder().build();
		BeanUtils.copyProperties(userRepo.save(user), dto);
		return dto;
	}

	@Override
	@Transactional(readOnly = false)
	public UserDto updateUser(User user, Integer userId) {
		// validation
		UserDto dto = null;
		User saveUser = null;
		
		saveUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		saveUser.setName(user.getName());
		saveUser.setEmailAddress(user.getEmailAddress());
		saveUser.setAbout(user.getAbout());
		saveUser.setPassword(user.getPassword());

		dto = UserDto.builder().build();
		BeanUtils.copyProperties(userRepo.save(saveUser), dto);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserById(Integer userId) {

		UserDto dto = null;
		User user = null;

		dto = UserDto.builder().build();
		System.out.println("first");
		user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		BeanUtils.copyProperties(user, dto);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAllUsers() {
		List<UserDto> dtos = null;

		dtos = userRepo.findAll().stream().map(user -> {
			UserDto dto = UserDto.builder().build();
			BeanUtils.copyProperties(user, dto);
			return dto;
		}).collect(Collectors.toList());

		return dtos;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);
	}

}
