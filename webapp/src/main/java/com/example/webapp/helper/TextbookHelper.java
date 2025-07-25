package com.example.webapp.helper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.form.TextbookForm;
import com.example.webapp.service.SubjectService;
import com.example.webapp.service.TextbookService;
import com.example.webapp.view.TextbookView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TextbookHelper {
	private final SubjectService subjectService;
	private final TextbookService textbookService;

	public void textBookList(Integer id, Model model) {
		List<TextbookView> view = textbookService.findAllTextbook(id);
		model.addAttribute("subject", subjectService.findOneSubjectById(id).get(0));
		//CollectionUtils.isEmptyは空とnullどっちも見れる
		//view.isEmpty(リスト型)のisEmptyは空しか見てくれない。
		if (!CollectionUtils.isEmpty(view)) {
			model.addAttribute("textbooks", textbookService.findAllTextbook(id));
		}
	}

	public boolean orMatch(TextbookForm form) {
		return textbookService.findAllTextbook(form.getSubjectId()).stream()
				.anyMatch(f -> f.getTitle().equals(form.getTitle()));
	}

	public void formRedirectView(TextbookForm form, RedirectAttributes attributes) {
		attributes.addAttribute("subjectId", form.getSubjectId());
		attributes.addAttribute("subjectName", form.getSubjectName());
		attributes.addAttribute("oldTitle", form.getOldTitle());
	}
}
