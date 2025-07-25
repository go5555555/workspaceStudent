package com.example.webapp.service;

import java.util.List;

import com.example.webapp.form.TextbookForm;
import com.example.webapp.view.TextbookView;

public interface TextbookService {
	
	List<TextbookView> findAllTextbook(Integer subjectId);
	
	void insertTextbook(TextbookForm form);
	
	void updateTextbook(TextbookForm form);
	
	void deleteTextbook(Integer id);

}
