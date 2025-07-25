package com.example.webapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Student;
import com.example.webapp.entity.StudyDetail;
import com.example.webapp.view.StudentPersonalInfoView;

@Mapper
public interface StudyDayMapper {
	//生徒詳細
	List<StudentPersonalInfoView> selectById(@Param("id") Integer studentId);
	
	Student selectByIdNew (@Param("id") Integer studentId);
	
	void insertD(StudyDetail entity);
	
	void updateD(StudyDetail entity);
	
	void deleteD(Integer studentId,LocalDate studyDay);
	
}
