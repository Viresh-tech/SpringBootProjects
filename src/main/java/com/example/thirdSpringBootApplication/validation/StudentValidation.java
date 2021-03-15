package com.example.thirdSpringBootApplication.validation;


import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class StudentValidation implements ConstraintValidator<NameValidation, String> {

	List<String> studentNames = Arrays.asList("abc", "xyz", "mno", "Sam", "John");


	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		return studentNames.contains(value);
	}

	
	public void initialize(NameValidation arg0) {
		// TODO Auto-generated method stub

	}

}
		