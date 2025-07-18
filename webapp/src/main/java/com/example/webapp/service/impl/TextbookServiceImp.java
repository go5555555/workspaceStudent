package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Textbook;
import com.example.webapp.form.TextbookForm;
import com.example.webapp.repository.TextbookMapper;
import com.example.webapp.service.TextbookService;
import com.example.webapp.view.TextbookView;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TextbookServiceImp implements TextbookService {
	private final TextbookMapper textbookMapper;

	@Override
	public List<TextbookView> findAllTextbook(Integer subjectId) {
		return textbookMapper.selectTextbookById(subjectId);
	}

	@Override
	public void insertTextbook(TextbookForm form) {
		Textbook entity = new Textbook();
		entity.setTitle(form.getTitle());
		entity.setSubjectId(form.getSubjectId());
		textbookMapper.insertText(entity);
	}

	@Override
	public void updateTextbook(TextbookForm form) {
		Textbook entity = new Textbook();
		entity.setTitle(form.getTitle());
		entity.setOldTitle(form.getOldTitle());
		entity.setSubjectId(form.getSubjectId());
		textbookMapper.updateText(entity);
	}
	
	public void deleteTextbook(Integer id) {
		textbookMapper.deleteText(id);
	}

}
