package com.example.thirdSpringBootApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thirdSpringBootApplication.POJO.Student;
import com.example.thirdSpringBootApplication.entity.StudentEntity;
import com.example.thirdSpringBootApplication.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repositry;

	public List<Student> getStudent() {

		List<Student> studentList = new ArrayList<Student>();
		List<StudentEntity> students = repositry.findAll();
		for (StudentEntity dto : students) {
			Student e = new Student();
			e.setStudentId(dto.getStudentId());
			e.setStudentAddress(dto.getStudentAddress());
			e.setStudentName(dto.getStudentName());
			studentList.add(e);

		}
		return studentList;
	}

	public Student createStudent(Student stu) {
		StudentEntity entity = new StudentEntity(stu);
		StudentEntity entityONe = repositry.save(entity);
		return new Student(entityONe);
	}

	public void deleteStudent(Integer id) {
		repositry.deleteById(id);
	}

	public Student updateStudent(Student stu) {
		StudentEntity entity = new StudentEntity(stu);
		StudentEntity entityONe = repositry.save(entity);
		return new Student(entityONe);

	}

	public Student getStudentById(Integer id) {
		StudentEntity entityONe = repositry.findByStudentId(id);
		Student stu = new Student(entityONe);
		return stu;
	}

}
