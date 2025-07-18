package com.example.webapp.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.form.SubjectForm;
import com.example.webapp.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubjectHelper {
	final private SubjectService subjectService;
	
	//fはsubjectService.findAllSubject()のListからanyMatchで取り出した要素
	public boolean orMatch(SubjectForm form) {
		return  subjectService.findAllSubject().stream().anyMatch(f -> f.getSubjectName().equals(form.getSubjectName()));
	}
	
	public void formRedirectView(SubjectForm form, RedirectAttributes attributes) {
		attributes.addAttribute("subjectId", form.getId());
		attributes.addAttribute("oldSubjectName", form.getOldSubjectName());
	}
}
