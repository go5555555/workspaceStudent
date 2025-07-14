package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Student;
import com.example.webapp.view.StudentView;

@Mapper
public interface StudentMapper {
	//生徒リスト
	List<Student>selectAll();
	
	//生徒一人の情報
	StudentView selectStudentById(@Param("id") Integer id);
	
	//生徒リストに生徒追加
	void insertS(Student entity);
	
	void updateS(Student entity);
	
	void deleteS(@Param("id") Integer id);
}
