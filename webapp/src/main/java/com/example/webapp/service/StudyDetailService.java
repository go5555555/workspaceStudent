package com.example.webapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.webapp.entity.StudyDetail;
import com.example.webapp.entity.Subject;
import com.example.webapp.entity.Textbook;
import com.example.webapp.form.StudyDetailForm;
import com.example.webapp.view.StudentStudyDetailView;

public interface StudyDetailService {

	List<StudentStudyDetailView> findByDate(Integer studentId, LocalDate studyDay);

	//StudentStudyDetailView toViewFromForm(StudyDetailForm form);

	List<Subject> findSubject();

	List<Textbook> findTextBook(Integer subjectId);

	StudyDetail toEntityFromForm(StudyDetailForm form);
	
	StudentStudyDetailView findSubjectAndTextbook(Integer textbookId,Integer id);

	void insertStudyDetail(StudyDetailForm form);

	void updateStudyDetail(StudyDetailForm form);

	void deleteStudyDetail(StudyDetailForm form);

}
