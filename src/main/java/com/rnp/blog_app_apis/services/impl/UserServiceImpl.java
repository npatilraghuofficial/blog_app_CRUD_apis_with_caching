package com.rnp.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import org.springframework.stereotype.Service;

import com.rnp.blog_app_apis.controllers.UserController;
import com.rnp.blog_app_apis.entities.User;
import com.rnp.blog_app_apis.exceptions.ResourceNotFoundException;
import com.rnp.blog_app_apis.payloads.UserDto;
import com.rnp.blog_app_apis.repositories.UserRepo;
import com.rnp.blog_app_apis.services.UserService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	// POST create user service implementation
	@Override
	@Cacheable(value = "users",key="#userDto.id")
		public UserDto createUser(UserDto userDto) {
	
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
		
		
	}
//patch method UPDATE user with id
	@Override
	@CachePut(value = "users",key="#userId")
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user","id",userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		System.out.println(userDto1.id+" user Details updated in database- userImpl ");

		return userDto1;
	
	}

	//caching is working for getAll user  GET method
	@Override
	@Cacheable(value = "users",key="#userId")
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
				System.out.println(user.getId() + "users id fetched from database - userImpl ");

		
		
		return this.userToDto(user);
	}

	@Override
	@Cacheable(value = "users")
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos =users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
				System.out.println("All users fetched from database- userImpl ");

		 
		return userDtos;
	}

	//caching is working for delete user  DELETE method
	@Override
	@CacheEvict(value = "users",key="#userId")
	public void delete(Integer userId) {
		User user = this.userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException( "User","Id",userId));
		this.userRepo.delete(user);
		System.out.println(user.getId()    +"Requested user Details deleted in database- userImpl ");
	}


	
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;

		
		
	}
	
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
		 
		
	}
}
