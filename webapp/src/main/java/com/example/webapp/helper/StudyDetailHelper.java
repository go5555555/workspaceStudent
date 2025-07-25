package com.example.webapp.helper;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.example.webapp.entity.Subject;
import com.example.webapp.form.StudyDetailForm;
import com.example.webapp.service.StudyDayService;
import com.example.webapp.service.StudyDetailService;
import com.example.webapp.view.StudentStudyDetailView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyDetailHelper {
	private final StudyDetailService studyDetailService;
	private final StudyDayService studentDayService;

	//training.htmlに一覧表示するメソッド
	public void trainingdetail(Integer studentId, LocalDate studyDay, Model model) {
		List<StudentStudyDetailView> view = studyDetailService.findByDate(studentId, studyDay);
		model.addAttribute("student", studentDayService.findByIdStudent(studentId).get(0)); // 共通処理
		model.addAttribute("studyDay", studyDay);
		//CollectionUtils.isEmptyはj空とnullどっちも見れる
		//view.isEmpty(リスト型)のisEmptyは空しか見てくれない。
		if (!CollectionUtils.isEmpty(view)) {
			model.addAttribute("training", view.get(0));
			model.addAttribute("trainings", view);
		}
	}

	public void toDetailForm(Model model) {
		List<Subject> subject = studyDetailService.findSubject();
		model.addAttribute("subjects", subject);
		//		表示が複雑になったら使うかも
		//		StudentStudyDetailView view = studyDetailService.toViewFromForm(form);
		//		model.addAttribute("student", view);
	}

	public void sanitizeForm(StudyDetailForm form) {
		if (form.getPages() == null || form.getPages().isEmpty()) {
			form.setPages(null);
		}
		if (form.getMemo() == null || form.getMemo().isEmpty()) {
			form.setMemo(null);
		}
	}

}
