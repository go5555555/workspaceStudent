package com.example.webapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Student;
import com.example.webapp.entity.StudyDetail;
import com.example.webapp.form.DayForm;
import com.example.webapp.repository.StudyDayMapper;
import com.example.webapp.service.StudyDayService;
import com.example.webapp.view.StudentPersonalInfoView;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyDayServiceImp implements StudyDayService {

	private final StudyDayMapper studyDayMapper;

	@Override
	public List<StudentPersonalInfoView> findByIdStudent(Integer studentId) {
		return studyDayMapper.selectById(studentId);
	}

	@Override
	public Student findByIdNewStudent(Integer studentId) {
		return studyDayMapper.selectByIdNew(studentId);
	}

	@Override
	public void insertDay(DayForm form) {
		StudyDetail entity = new StudyDetail();
		entity.setStudentId(form.getStudentId());
		entity.setStudyDay(form.getStudyDay());
		studyDayMapper.insertD(entity);
	}

	@Override
	public void updateDay(DayForm form) {
		StudyDetail entity = new StudyDetail();
		entity.setStudentId(form.getStudentId());
		entity.setStudyDay(form.getStudyDay());
		entity.setOldStudyDay(form.getOldStudyDay());
		studyDayMapper.updateD(entity);
	}
	
	@Override
	public void deleteDay(Integer studentId,LocalDate studyDay) {
		studyDayMapper.deleteD(studentId,studyDay);
	}

}
