package com.example.thirdSpringBootApplication.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.thirdSpringBootApplication.POJO.Bank;
import com.example.thirdSpringBootApplication.POJO.Department;
import com.example.thirdSpringBootApplication.POJO.DepartmentException;
import com.example.thirdSpringBootApplication.POJO.Student;
import com.example.thirdSpringBootApplication.aop.Logged;
import com.example.thirdSpringBootApplication.service.StudentService;

@RestController
@Validated
public class StudentController {

	@Value("${name}")
	private String value;

	@Value("${fName}")
	private String firtsName;

	@Autowired
	private Department dept;

	@Autowired
	private Bank jp;

	@Autowired
	private StudentService mockService;

	@Logged
	@RequestMapping(method = RequestMethod.GET, value = "/bank")
	public String getBank() {
		return jp.getBankName();
	}

	List<String> colleges = Arrays.asList("Harward", "IIT", "Oxford", "Cambridge");

	List<Student> listStudents = Arrays.asList(new Student(101, "ABC", "NJ"), new Student(102, "XYZ", "NY"),
			new Student(103, "MNO", "LA"));

	@Logged
	@GetMapping("/greet")
	public String greetings() {
		return firtsName;
	}

	@Logged
	@RequestMapping(method = RequestMethod.GET, value = "/check")
	public String exceptionCheck() throws DepartmentException {
		throw new DepartmentException(404, "Page Not Found");
	}

	@Logged
	@GetMapping("/listColleges")
	public List<String> getUniversity() {
		return colleges;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findById")
	public Student students(@RequestParam("id") Integer id) {
		return mockService.getStudentById(id);
	}

	@GetMapping("/student")
	public List<Student> getStudent() {
		return mockService.getStudent();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public Student createStudent(@Valid @RequestBody Student stu) {
		return mockService.createStudent(stu);	
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/student")
	public void deleteStudent(@RequestParam("id") Integer id) {
		mockService.deleteStudent(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/student")
	public void updateStudent(@RequestBody Student stu) {
		mockService.updateStudent(stu);
	}

	
}
