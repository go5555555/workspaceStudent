package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.form.SubjectForm;
import com.example.webapp.helper.SubjectHelper;
import com.example.webapp.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("subjects")
@RequiredArgsConstructor
public class SubjectController {
	private final SubjectService subjectService;
	private final SubjectHelper subjectHelper;

	@GetMapping
	public String subjectList(Model model) {
		model.addAttribute("subjects", subjectService.findAllSubject());
		if(!subjectService.findAllSubject().isEmpty()) {
		model.addAttribute("subject", subjectService.findAllSubject().get(0));
		}
		return "subject/subject";
	}

	@GetMapping("/form")
	public String newSubject(@ModelAttribute SubjectForm form) {
		form.setIsNewSub(true);
		return "subject/form/subjectform";
	}

	@PostMapping("/save")
	public String createSubject(@Validated SubjectForm form,
			Model model,
			BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors() || form.getSubjectName().isEmpty()) {
			attributes.addFlashAttribute("errorMessage", "科目を追加できません");
			return "redirect:/subjects/form";
		}

		//既に登録済みの科目名の場合の処理
		if (subjectHelper.orMatch(form)) {
			attributes.addFlashAttribute("errorMessage", "既に登録済みの教科です");
			return "redirect:/subjects/form";
		}

		//formをentityに保存するためのメソッドを呼ぶ
		subjectService.insertSubject(form.getSubjectName());
		attributes.addFlashAttribute("message", "新しい科目が登録されました");
		return "redirect:/subjects";
	}

	//科目名を更新するための画面へ遷移
	@GetMapping("/form/{id}")
	public String changeSubject(@ModelAttribute SubjectForm form, Model model) {
		form.setIsNewSub(false);
		model.addAttribute("subjectForm", form);
		return "subject/form/subjectform";
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@PostMapping("/update")
	public String updateStudent(@Validated SubjectForm form, Model model, BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors() || form.getSubjectName().isEmpty()) {
			subjectHelper.formRedirectView(form,attributes);
			attributes.addFlashAttribute("errorMessage", "更新されてません");
			return "redirect:/subjects/form/" + form.getId();
		}

		if (subjectHelper.orMatch(form)) {
			subjectHelper.formRedirectView(form,attributes);
			attributes.addFlashAttribute("errorMessage", "既に登録済みの教科名です");
			return "redirect:/subjects/form/" + form.getId();
		}
		//整形したデータを送って更新。DBでnullだった場合は更新しない処理にしてる
		subjectService.updateSubject(form);
		model.addAttribute("subjects", subjectService.findAllSubject());
		attributes.addFlashAttribute("message", "科目名を更新しました");
		return "redirect:/subjects";
	}

	//科目情報削除の確認画面へ遷移
	@GetMapping("/reallydelete/{id}")
	public String reallyDeleteStudent(@ModelAttribute SubjectForm form) {
		return "subject/reallydeletesubject";
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@GetMapping("/delete")
	public String deleteSubject(Integer id, RedirectAttributes attributes) {
		subjectService.deleteSubject(id);
		attributes.addFlashAttribute("message", "科目を削除しました");
		return "redirect:/subjects";
	}
}
