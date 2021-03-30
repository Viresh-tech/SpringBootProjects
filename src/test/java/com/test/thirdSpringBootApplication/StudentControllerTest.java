package com.test.thirdSpringBootApplication;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.resetAll;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.thirdSpringBootApplication.POJO.DepartmentException;
import com.example.thirdSpringBootApplication.POJO.Student;
import com.example.thirdSpringBootApplication.controller.StudentController;
import com.example.thirdSpringBootApplication.service.StudentService;
import com.test.thirdSpringBootApplication.config.TestConfiguration;

@ContextConfiguration(classes = { TestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentControllerTest {

	@Autowired
	private StudentController controller;

	@Autowired
	private StudentService mockService;

	@Autowired
	private StudentService realService;

	private static String name;
	private Integer id;

	@BeforeClass
	public static void setUp() {

		System.out.println("inside the before class method");
		name = "xyz";
	}

	@AfterClass
	public static void afterCla() {

		System.out.println("inside the final after class method");

	}

	@Before
	public void before() {
		id = 101;
		System.out.println("inside the before class method of each method");

	}

	@Test
	public void test_getName() {

		Student s1 = new Student(101, "abc", "NY");

		expect(mockService.getStudentById(101)).andReturn(s1);

		replayAll();
		Student student = controller.students(101);
		resetAll();

		assertNotNull(student);
		assertEquals(student.getStudentId(), 101);
		assertEquals(student.getStudentName(), "abc");
		assertEquals(student.getStudentAddress(), "NY");

	}

	@Test
	public void test_createStudent() {

		Student s1 = new Student(101, name, "NY");

		expect(mockService.createStudent(s1)).andReturn(s1).once();

		replayAll();
		ResponseEntity<Student> response = controller.createStudent(s1);
		resetAll();
		Student student = response.getBody();
		assertNotNull(student);
		assertEquals(student.getStudentId(), 101);
		assertEquals(student.getStudentName(), name);
		assertEquals(student.getStudentAddress(), "NY");

	}

	@Test(expected = DepartmentException.class)
	@Ignore
	public void test_getUniversity() throws DepartmentException {

		controller.getUniversity();

	}

	@Test
	public void test_getStudent() {

		List<Student> list = Arrays.asList(new Student(101, "abc", "NY"), new Student(102, name, "NJ"),
				new Student(103, "mno", "LA"));

		expect(mockService.getStudent()).andReturn(list).once();
		replayAll();
		ResponseEntity<List<Student>> response = controller.getStudent();
		resetAll();
		List<Student> students = response.getBody();

		assertNotNull(students);
		assertEquals(students.get(0).getStudentId(), 101);
		assertEquals(students.get(0).getStudentName(), "abc");
		assertEquals(students.get(0).getStudentAddress(), "NY");
		assertEquals(students.get(1).getStudentId(), 102);
		assertEquals(students.get(1).getStudentName(), name);
		assertEquals(students.get(1).getStudentAddress(), "NJ");

	}

}
