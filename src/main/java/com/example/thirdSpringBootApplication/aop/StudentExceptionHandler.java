package com.example.thirdSpringBootApplication.aop;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.thirdSpringBootApplication.POJO.DepartmentException;
import com.example.thirdSpringBootApplication.POJO.StudentException;

@RestControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler
	public String logException(StudentException ex) {
		return ex.getMessag();
	}
	

	@ExceptionHandler
	public String logDepartmentException(DepartmentException ex) {
		return ex.getMessag();
	}

	@ExceptionHandler
	public String logDepartmentException(MethodArgumentNotValidException ex) {
		return ex.getMessage();
	}

}
