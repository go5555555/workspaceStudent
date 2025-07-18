package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.form.TextbookForm;
import com.example.webapp.helper.TextbookHelper;
import com.example.webapp.service.TextbookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("textbooks")
@RequiredArgsConstructor
public class TextbookController {
	private final TextbookService textbookService;
	private final TextbookHelper textbookHelper;

	@GetMapping("/{subjectId}")
	public String textBookList(@PathVariable Integer subjectId, Model model) {
		textbookHelper.textBookList(subjectId, model);
		return "subject/textbook";
	}

	@GetMapping("/form")
	public String newTextBook(@ModelAttribute TextbookForm form) {
		form.setIsNewText(true);
		return "subject/form/textbookform";
	}

	@PostMapping("/save")
	public String createTextBook(@Validated @ModelAttribute TextbookForm form,
			Model model,
			BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors() || form.getTitle().isEmpty()) {
			textbookHelper.formRedirectView(form, attributes);
			attributes.addFlashAttribute("errorMessage", "参考書を追加できません");
			return "redirect:/textbooks/form";
		}

		//既に登録済みの科目名の場合の処理
		if (textbookHelper.orMatch(form)) {
			textbookHelper.formRedirectView(form, attributes);
			attributes.addFlashAttribute("errorMessage", "既に登録済みの参考書です");
			return "redirect:/textbooks/form";
		}

		//formをentityに保存するためのメソッドを呼ぶ
		textbookService.insertTextbook(form);
		attributes.addFlashAttribute("message", "新しい参考書が登録されました");
		return "redirect:/textbooks/" + form.getSubjectId();
	}

	//科目名を更新するための画面へ遷移
	@GetMapping("/form/{id}")
	public String changeTextBook(@ModelAttribute TextbookForm form, Model model) {
		form.setIsNewText(false);
		return "subject/form/textbookform";
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@PostMapping("/update")
	public String updateTextBook(@Validated TextbookForm form
			, Model model
			, BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors() || form.getTitle().isEmpty()) {
			textbookHelper.formRedirectView(form, attributes);
			attributes.addFlashAttribute("errorMessage", "更新されてません");
			return "redirect:/textbooks/form/" + form.getSubjectId();
		}

		if (textbookHelper.orMatch(form)) {
			textbookHelper.formRedirectView(form, attributes);
			attributes.addFlashAttribute("errorMessage", "既に登録済みの教科名です");
			return "redirect:/textbooks/form/" + form.getSubjectId();
		}
		//整形したデータを送って更新。DBでnullだった場合は更新しない処理にしてる
		textbookService.updateTextbook(form);
		attributes.addAttribute("id", form.getSubjectId());
		attributes.addFlashAttribute("message", "科目名を更新しました");
		return "redirect:/textbooks/" + form.getSubjectId();
	}

	@GetMapping("/delete")
	public String deleteDetail(@RequestParam Integer id
			,@RequestParam Integer subjectId
			, Model model) {
		textbookService.deleteTextbook(id);
		textbookHelper.textBookList(subjectId,model);
		model.addAttribute("message", "参考書を削除しました");
		return "subject/textbook";
	}
}
