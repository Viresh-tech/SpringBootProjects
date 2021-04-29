package com.example.thirdSpringBootApplication.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.thirdSpringBootApplication.POJO.AuthenticationRequests;
import com.example.thirdSpringBootApplication.POJO.AuthenticationResponse;
import com.example.thirdSpringBootApplication.POJO.Bank;
import com.example.thirdSpringBootApplication.POJO.Department;
import com.example.thirdSpringBootApplication.POJO.DepartmentException;
import com.example.thirdSpringBootApplication.POJO.Student;
import com.example.thirdSpringBootApplication.aop.Logged;
import com.example.thirdSpringBootApplication.service.JwtUtils;
import com.example.thirdSpringBootApplication.service.MyUserDetailsService;
import com.example.thirdSpringBootApplication.service.Producer;
import com.example.thirdSpringBootApplication.service.StudentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Validated
public class StudentController {

	public static String currentRole;

	@Autowired
	private Producer producer;

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private MyUserDetailsService service;

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
	@GetMapping("/allStudent")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> students = (List<Student>) template.query("select * from student_Entity",
								new RowMapper<Student>() {
					@Override
					public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

						Student student = new Student();
						student.setStudentId(rs.getInt("id"));
						student.setStudentName(rs.getString("student_Name"));
						student.setStudentAddress(rs.getString("student_Address"));

						return student;

					}

				});
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

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
		if (currentRole == "ADMIN")
			return new ResponseEntity<>(firtsName, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping("/admin")
	public String admin() {
		return "hello admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "hell user";
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
	@ApiOperation(value = "Fetch student", notes = "This method fetches a student on the basis of id")
	public Student students(
			@ApiParam(name = "id", type = "Integer", value = "Id of the student", required = true) @RequestParam("id") Integer id) {
		return mockService.getStudentById(id);

	}

	@CrossOrigin
	@GetMapping("/student")
	public ResponseEntity<List<Student>> getStudent() {

		return new ResponseEntity<>(mockService.getStudent(), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "stu", value = "List of Student", paramType = "body", dataType = "Student") })
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student stu) {

		return new ResponseEntity<>(mockService.createStudent(stu), HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/student")
	@ApiOperation(value = "Delete student", notes = "This method deletes a student on the basis of id")
	public ResponseEntity<Void> deleteStudent(
			@ApiParam(name = "id", type = "Integer", value = "Id of the student", required = true) @RequestParam("id") Integer id) {
		mockService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Logged
	@RequestMapping(method = RequestMethod.PUT, value = "/student")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "stu", value = "List of Student", paramType = "body", dataType = "Student") })
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

	@GetMapping("/logout")
	public String logout() {

		return "User Successfully logged out";

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> createAutheticationToken(@RequestBody AuthenticationRequests request)
			throws Exception {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorrect UserName and Password");
		}

		final UserDetails userDetails = service.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtils.generateToken(userDetails);
		AuthenticationResponse authResponse = new AuthenticationResponse(jwt);
		// return ResponseEntity.ok(new AuthenticationResponse(jwt));
		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}

	@Value("${server.port}")
	private String port;

	@GetMapping("/port")
	public String getPort() {
		return port;
	}

}
