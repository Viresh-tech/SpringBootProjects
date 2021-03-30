package com.example.thirdSpringBootApplication.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.thirdSpringBootApplication.service.Producer;
import com.example.thirdSpringBootApplication.service.StudentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@Validated
public class StudentController {

	@Autowired
	private Producer producer;

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

	@CrossOrigin
	@Logged
	@RequestMapping(method = RequestMethod.GET, value = "/bank")
	public List<String> getBank() {
		return Arrays.asList(jp.getBankName());
	}

	List<String> colleges = Arrays.asList("Harward", "IIT", "Oxford", "Cambridge");

	List<Student> listStudents = Arrays.asList(new Student(101, "ABC", "NJ"), new Student(102, "XYZ", "NY"),
			new Student(103, "MNO", "LA"));

	@Logged
	@GetMapping("/greet")
	public ResponseEntity<String> greetings() {
		return new ResponseEntity<>(firtsName, HttpStatus.OK);
	}

	@Logged
	@RequestMapping(method = RequestMethod.GET, value = "/check")
	public String exceptionCheck() throws DepartmentException {
		throw new DepartmentException(404, "Page Not Found");
	}

	@CrossOrigin
	@Logged
	@GetMapping("/listColleges")
	public List<String> getUniversity() {
		return colleges;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findById")
	public Student students(@RequestParam("id") Integer id) {
		return mockService.getStudentById(id);

	}

	@CrossOrigin
	@GetMapping("/student")
	public ResponseEntity<List<Student>> getStudent() {

		return new ResponseEntity<>(mockService.getStudent(), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student stu) {

		return new ResponseEntity<>(mockService.createStudent(stu), HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/student")
	public ResponseEntity<Void> deleteStudent(@RequestParam("id") Integer id) {
		mockService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Logged
	@RequestMapping(method = RequestMethod.PUT, value = "/student")
	public ResponseEntity<Void> updateStudent(@RequestBody Student stu) {
		mockService.updateStudent(stu);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping("/checkHystric")
	@HystrixCommand(fallbackMethod = "defaultMessage")
	public String checkHystrix() throws InterruptedException {
		Thread.sleep(3000);
		return "viresh";

	}

	private String defaultMessage() {

		return "Hello User! Thread is sleeping";
	}

	@GetMapping("/publish")
	public void messageToTopic(@RequestParam String message) {
		producer.sendMessages(message);
	}

}
