package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Student;
import com.example.webapp.form.StudentForm;
import com.example.webapp.helper.StudentHelper;
import com.example.webapp.repository.StudentMapper;
import com.example.webapp.service.StudentService;
import com.example.webapp.view.StudentView;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentMapper studentMapper;
	private final StudentHelper studentHelper;

	@Override
	public List<Student> findAllService() {
		// TODO 自動生成されたメソッド・スタブ
		return studentMapper.selectAll();
	}

	@Override
	public StudentView findOneService(Integer id) {
		return studentMapper.selectStudentById(id);
	}

	@Override
	public void insertStudent(StudentForm form) {
		Student entity = studentHelper.toEntityFromForm(form);
		studentMapper.insertS(entity);
	}

	@Override
	public void updateStudent(StudentForm form) {
		Student entity = studentHelper.toEntityFromForm(form);
		studentMapper.updateS(entity);
	}

	@Override
	public void deleteStudent(Integer id) {
		studentMapper.deleteS(id);
	};

}
