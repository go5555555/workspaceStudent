package com.example.webapp.helper;

import org.springframework.stereotype.Component;

import com.example.webapp.entity.Student;
import com.example.webapp.form.StudentForm;

@Component
public class StudentHelper {
	//生徒登録フォームを生徒エンティティに変換
	public Student toEntityFromForm(StudentForm form) {
		Student entity = new Student();
		entity.setId(form.getId());
		entity.setStudentName(form.getStudentName());
		entity.setGrade(form.getGrade());
		return entity;
	}

	public void sanitizeForm(StudentForm form) {
		if(form.getStudentName() == null || form.getStudentName().isEmpty()) {
			form.setStudentName(null);
		}
		if(form.getGrade() == null || form.getGrade().equals(0)) {
			form.setGrade(null);
		}
	}
	
	public void inNull(StudentForm form) {
		form.setStudentName(null);
		form.setGrade(null);
	}
	
}