package com.example.webapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.StudyDetail;
import com.example.webapp.entity.Subject;
import com.example.webapp.entity.Textbook;
import com.example.webapp.view.StudentStudyDetailView;

@Mapper
public interface StudyDetailMapper {

	//指導日と生徒ID→指導詳細         ここのParamのstudyDayはSQLのWHEREとつながってる
	//                                 paramの右の値はビューからの値を受け取ってる
	List<StudentStudyDetailView> selectByDate(@Param("id") Integer studentId, @Param("studyDay") LocalDate studyDay);

	List<Subject> selectSubject();

	List<Textbook> selectTextBook(@Param("subjectId") Integer subjectId);
	
	StudentStudyDetailView selectSubAndTextByTextId(Integer textbookId,Integer id);

	void insertSD(StudyDetail entity);

	void updateSD(StudyDetail entity);

	void deleteSD(StudyDetail entity);

}
