package com.rnp.blog_app_apis.controllers;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.rnp.blog_app_apis.payloads.ApiResponse;
import com.rnp.blog_app_apis.payloads.UserDto;
import com.rnp.blog_app_apis.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
	

    
	@Autowired
	private UserService userService;
	
	
	//CREATE user API
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		System.out.println("Created user in the database: " + createUserDto);

		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	} 
	
	//UPDATE USER API
	//put method update user with id
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}

	
	//Delete USER API
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser( @PathVariable("userId") Integer uid){
		
		this.userService.delete(uid);
		return new  ResponseEntity<ApiResponse>(new ApiResponse("user Deleted Succesfully",true),HttpStatus.OK);
		
	}
	
	// Get user API
	@GetMapping("/") 
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
		}
	
	//get user by id
		@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
		}
	
	
	
	
	

}
