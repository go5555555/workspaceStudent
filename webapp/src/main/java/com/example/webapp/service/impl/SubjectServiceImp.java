package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Subject;
import com.example.webapp.form.SubjectForm;
import com.example.webapp.repository.SubjectMapper;
import com.example.webapp.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectServiceImp implements SubjectService {
	private final SubjectMapper subjectMapper;

	@Override
	public List<Subject> findAllSubject() {
		return subjectMapper.selectAllSub();
	}
	
	@Override
	public List<Subject> findOneSubjectById(Integer id) {
		return subjectMapper.selectOneSub(id);
	}
	
	@Override
	public void insertSubject(String subjectName) {
		subjectMapper.insertSub(subjectName);
	}
	
	@Override
	public void updateSubject(SubjectForm form) {
		Subject entity = new Subject();
		entity.setId(form.getId());
		entity.setSubjectName(form.getSubjectName());
		entity.setOldSubjectName(form.getOldSubjectName());
		subjectMapper.updateSub(entity);
	}
	
	@Override
	public void deleteSubject(Integer id) {
		subjectMapper.deleteSub(id);
	}
}
