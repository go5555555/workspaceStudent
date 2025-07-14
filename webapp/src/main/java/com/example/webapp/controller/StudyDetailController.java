package com.example.webapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.Textbook;
import com.example.webapp.form.StudyDetailForm;
import com.example.webapp.helper.StudyDetailHelper;
import com.example.webapp.service.StudyDetailService;
import com.example.webapp.view.StudentStudyDetailView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/details")
@RequiredArgsConstructor
public class StudyDetailController {
	//DIしてる
	private final StudyDetailService studyDetailService;
	private final StudyDetailHelper studyDetailHelper;

	//フォームはオブジェクトを引数にして、IDとかを受け取り、エンティティにGETで渡す
	//フォームを介さないで id と studyDay　を送ってる
	@GetMapping("")
	public String training(@RequestParam Integer studentId,
			@RequestParam LocalDate studyDay,
			Model model,
			RedirectAttributes attributes) {
		studyDetailHelper.trainingdetail(studentId, studyDay, model);
		return "student/training";
	}

	//学習内容登録画面への遷移
	@GetMapping("/form")
	public String newDetail(@ModelAttribute StudyDetailForm form,
			Model model) {
		form.setIsNewDetail(true);
		studyDetailHelper.toDetailForm(model);
		return "student/detailform";
	}

	//科目IDから参考書一覧を取得する。JavaScriptで使う
	@GetMapping("/textbooks")
	@ResponseBody
	public List<Textbook> getTextbooks(@RequestParam("id") Integer subjectId) {
		return studyDetailService.findTextBook(subjectId);
	}

	@PostMapping("/studysave")
	public String create(@Validated StudyDetailForm form, Model model, BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			form.setIsNewDetail(true);
			attributes.addFlashAttribute("message", "更新できませんでした");
			return "redirect:/detailform";
		}
		try {
			studyDetailService.insertStudyDetail(form);
			studyDetailHelper.trainingdetail(form.getStudentId(), form.getStudyDay(), model);
			model.addAttribute("message", "新しい学習内容を記録しました");
			return "student/training";
		} catch (Exception e) {
			form.setIsNewDetail(true);
			studyDetailHelper.toDetailForm(model);
			model.addAttribute("message", "既に登録されてる学習内容です");
			return "student/detailform";
		}
	}

	//学習内容更新画面への遷移
	@GetMapping("/form/{studentId}")
	public String changeDetail(@ModelAttribute StudyDetailForm form,
			Model model) {
		StudentStudyDetailView view = studyDetailService.findSubjectAndTextbook(form.getOldTextbookId(),form.getId());
		model.addAttribute("subtext",view);
		form.setIsNewDetail(false);
		studyDetailHelper.toDetailForm(model);
		return "student/detailform";
	}

	@PostMapping("/studyupdate")
	public String updateDetail(@Validated StudyDetailForm form, Model model, BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			form.setIsNewDetail(false);
			attributes.addFlashAttribute("message", "更新できませんでした");
			return "redirect:/detailform";
		}
		
		
		try {
			studyDetailHelper.sanitizeForm(form);
			studyDetailService.updateStudyDetail(form);
			studyDetailHelper.trainingdetail(form.getStudentId(), form.getStudyDay(), model);
			model.addAttribute("message", "新しい学習内容を更新しました");
			return "student/training";
		} catch (Exception e) {
			form.setIsNewDetail(false);
			studyDetailHelper.toDetailForm(model);
			model.addAttribute("message", "更新できてません");
			return "student/detailform";
		}
	}

	@GetMapping("/delete")
	public String deleteDetail(StudyDetailForm form, Model model) {
		studyDetailService.deleteStudyDetail(form);
		studyDetailHelper.trainingdetail(form.getStudentId(), form.getStudyDay(), model);
		model.addAttribute("message", "学習詳細を削除しました");
		return "student/training";
	}

}
