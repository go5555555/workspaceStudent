package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.Student;
import com.example.webapp.form.StudentForm;
import com.example.webapp.view.StudentView;

public interface StudentService {
	
	List<Student>findAllService();
	
	StudentView findOneService(Integer id);
	
	void insertStudent(StudentForm form);
	
	void updateStudent(StudentForm form);
	
	void deleteStudent(Integer id);

}
