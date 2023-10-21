package com.rnp.blog_app_apis.payloads;


import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	@NotNull
	public int id;
	
	@NotNull
	public String name;
	
	@NotNull
	public String password;
	
	@NotNull
	public String about;
	
	@Email
	public String email;
	
	
}
