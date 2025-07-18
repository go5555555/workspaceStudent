package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Textbook;
import com.example.webapp.view.TextbookView;

@Mapper
public interface TextbookMapper {
	
	List<TextbookView> selectTextbookById(@Param("subjectId")Integer id);
	
	void insertText(Textbook entity);
	
	void updateText(Textbook entity);
	
	void deleteText(Integer id);

}
