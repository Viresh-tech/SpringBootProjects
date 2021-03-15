package com.test.thirdSpringBootApplication.config;

import org.powermock.api.easymock.PowerMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.thirdSpringBootApplication.POJO.Bank;
import com.example.thirdSpringBootApplication.POJO.Department;
import com.example.thirdSpringBootApplication.POJO.JPMorgan;
import com.example.thirdSpringBootApplication.controller.StudentController;
import com.example.thirdSpringBootApplication.repository.StudentRepository;
import com.example.thirdSpringBootApplication.service.StudentService;

@Configuration
public class TestConfiguration {

	@Bean
	public StudentController getStudentController() {
		return new StudentController();
	}

	@Bean(name="mockService")
	public StudentService getStudentService() {
		return PowerMock.createMock(StudentService.class);
	}

	@Bean(name="realService")
	public StudentService studentService() {
		return new StudentService();
	}

	@Bean
	public Department getDepartment() {
		return new Department();
	}

	@Bean
	public Bank getBank() {
		return new JPMorgan();
	}

	@Bean
	public StudentRepository getStudentRepository() {
		return PowerMock.createMock(StudentRepository.class);
	}

}
