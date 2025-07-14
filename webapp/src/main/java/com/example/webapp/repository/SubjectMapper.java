package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.webapp.entity.Subject;

@Mapper
public interface SubjectMapper {

	List<Subject> selectAllSub();
	
	List<Subject> selectOneSub(Integer id);
	
	void insertSub(String subjectName);
	
	void updateSub(Subject entity);
	
	void deleteSub(Integer id);
}
