package com.example.thirdSpringBootApplication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.thirdSpringBootApplication.entity.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
	@SuppressWarnings("unchecked")
	StudentEntity save(StudentEntity student);

	List<StudentEntity> findAll();
	
	void deleteById(Integer id);
	
	StudentEntity findByStudentId(Integer id);

}
