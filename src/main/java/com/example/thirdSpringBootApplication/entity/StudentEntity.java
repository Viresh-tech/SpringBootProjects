package com.example.thirdSpringBootApplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.thirdSpringBootApplication.POJO.Student;

@Entity
@Table(name = "student_Entity")
public class StudentEntity {

	public StudentEntity() {
		

	}

	public StudentEntity(Student stu) {
		this.studentName = stu.getStudentName();
		this.studentId = stu.getStudentId();
		this.studentAddress = stu.getStudentAddress();
	}

	public StudentEntity(int studentId, String studentName, String studentAddress) {

		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAddress = studentAddress;
	}

	@Id
	@Column(name="id")
	private int studentId;
	@Column(name="student_Name")
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
	@Column(name="student_Address")
	private String studentAddress;

}
