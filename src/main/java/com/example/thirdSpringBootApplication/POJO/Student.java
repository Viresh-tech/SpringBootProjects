package com.example.thirdSpringBootApplication.POJO;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.thirdSpringBootApplication.entity.StudentEntity;
import com.example.thirdSpringBootApplication.validation.NameValidation;

public class Student {

	public Student() {

	}

	public Student(StudentEntity stu) {
		this.studentId = stu.getStudentId();
		this.studentName = stu.getStudentName();
		this.studentAddress = stu.getStudentAddress();
	}

	public Student(int studentId, String studentName, String studentAddress) {

		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAddress = studentAddress;
	}

	@NotNull
	private int studentId;

	@NameValidation
	@NotBlank
	private String studentName;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	private String studentAddress;

}
