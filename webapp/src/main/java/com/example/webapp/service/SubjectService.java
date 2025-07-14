package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.Subject;
import com.example.webapp.form.SubjectForm;

public interface SubjectService {

	List<Subject> findAllSubject();
	
	List<Subject>findOneSubjectById(Integer id);

	void insertSubject(String subjectName);

	void updateSubject(SubjectForm form);
	
	void deleteSubject(Integer id);
}
