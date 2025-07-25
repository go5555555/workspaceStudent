package com.example.webapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.webapp.entity.Student;
import com.example.webapp.form.DayForm;
import com.example.webapp.view.StudentPersonalInfoView;

public interface StudyDayService {
	
	List<StudentPersonalInfoView> findByIdStudent(Integer studentId);
	
	public Student findByIdNewStudent(Integer studentId);
	
	void insertDay(DayForm form);
	
	void updateDay(DayForm form);
	
	void deleteDay(Integer studentId,LocalDate studyDay);
}
