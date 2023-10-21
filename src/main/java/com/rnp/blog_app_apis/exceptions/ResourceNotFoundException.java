package com.rnp.blog_app_apis.exceptions;

import com.fasterxml.jackson.core.sym.Name;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with this Name %s : %s" , resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	 
	
}
