package com.rnp.blog_app_apis.services;

import java.util.List;


import com.rnp.blog_app_apis.payloads.UserDto;

public interface UserService {
	
	 UserDto createUser(UserDto userDto);
	 UserDto updateUser(UserDto user, Integer userId);
	 UserDto getUserById(Integer userId);
	 List<UserDto> getAllUsers();
	 
	 
	 void delete(Integer userId);

}
 