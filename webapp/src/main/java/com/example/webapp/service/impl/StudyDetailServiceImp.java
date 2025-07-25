package com.example.webapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.StudyDetail;
import com.example.webapp.entity.Subject;
import com.example.webapp.entity.Textbook;
import com.example.webapp.form.StudyDetailForm;
import com.example.webapp.repository.StudyDetailMapper;
import com.example.webapp.service.StudyDetailService;
import com.example.webapp.view.StudentStudyDetailView;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyDetailServiceImp implements StudyDetailService {
	//DIしてる？
	private final StudyDetailMapper studyDetailMapper;

	@Override
	public List<StudentStudyDetailView> findByDate(Integer studentId, LocalDate studyDay) {
		return studyDetailMapper.selectByDate(studentId, studyDay);
	}

	//  使ってない
	//	@Override
	//	public StudentStudyDetailView toViewFromForm(StudyDetailForm form) {
	//		StudentStudyDetailView view = new StudentStudyDetailView();
	//		view.setStudentId(form.getStudentId()); // ← studentId をセット
	//		view.setStudentName(form.getStudentName());
	//		view.setStudyDay(form.getStudyDay());
	//	return view;
	//	}

	@Override
	public List<Subject> findSubject() {
		return studyDetailMapper.selectSubject();
	}

	@Override
	public List<Textbook> findTextBook(Integer subjectId) {
		return studyDetailMapper.selectTextBook(subjectId);
	}
	@Override
	public StudentStudyDetailView findSubjectAndTextbook(Integer textbookId, Integer id) {
		return studyDetailMapper.selectSubAndTextByTextId(textbookId,id);
	}

	@Override
	public StudyDetail toEntityFromForm(StudyDetailForm form) {
		StudyDetail entity = new StudyDetail();
		entity.setStudentId(form.getStudentId());
		entity.setStudyDay(form.getStudyDay());
		entity.setTextBookId(form.getTextbookId());
		entity.setOldTextBookId(form.getOldTextbookId());
		entity.setPages(form.getPages());
		entity.setMemo(form.getMemo());
		return entity;
	}

	@Override
	public void insertStudyDetail(StudyDetailForm form) {
		studyDetailMapper.insertSD(toEntityFromForm(form));
	}

	@Override
	public void updateStudyDetail(StudyDetailForm form) {
		studyDetailMapper.updateSD(toEntityFromForm(form));
	}

	@Override
	public void deleteStudyDetail(StudyDetailForm form) {
		studyDetailMapper.deleteSD(toEntityFromForm(form));
	}

}
