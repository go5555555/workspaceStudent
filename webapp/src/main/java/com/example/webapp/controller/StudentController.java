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

import com.example.webapp.form.StudentForm;
import com.example.webapp.helper.StudentHelper;
import com.example.webapp.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	private final StudentHelper studentHelper;
	
	//生徒一覧を表示する
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students",studentService.findAllService());
		return "student/list";
	}

	//生徒を登録するための画面へ遷移
	@GetMapping("/form")
	public String newStudent(@ModelAttribute StudentForm form) {
		form.setIsNew(true);
		return "student/studentform";
	}
	
	//formで受け取りvalidetedしてerrorがなければぬける
	@PostMapping("/save")
	public String create(@Validated StudentForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes) {
		if(bindingResult.hasErrors()) {
			form.setIsNew(true);
			return "student/studentform";
		}
		//formをentityに保存するためのメソッドを呼ぶ
		studentService.insertStudent(form);
		attributes.addFlashAttribute("message","新しい生徒が登録されました");
		return "redirect:/students";
		}
	
	//生徒情報を更新するための画面へ遷移
	@GetMapping("/form/{id}")
	public String changeStudent(@ModelAttribute StudentForm form
								,Model model) { 
		model.addAttribute("student",studentService.findOneService(form.getId()));
		form.setIsNew(false);
		return "student/studentform";
	}
	//formで受け取りvalidetedしてerrorがなければぬける
	@PostMapping("/update")
	public String updateStudent(@Validated StudentForm form
			,Model model
			,BindingResult bindingResult
			,RedirectAttributes attributes) {
		if(bindingResult.hasErrors() || form.getStudentName().isEmpty() && form.getGrade().equals(0)) {
			form.setIsNew(false);
			attributes.addFlashAttribute("errorMessage","何も更新されてないよ");
			return "redirect:/students/form/"+ form.getId();
		}
		//更新画面で名前と学年のどちらかが未入力の場合、未入力にnullを入れる
		studentHelper.replaceEmptyFieldsWithNull(form);
		//整形したデータを送って更新。DBでnullだった場合は更新しない処理にしてる
		studentService.updateStudent(form);
		model.addAttribute("students",studentService.findAllService());
		attributes.addFlashAttribute("message","生徒情報を更新しました");
		return "redirect:/students";
		}
	
	//生徒情報削除の確認画面へ遷移
		@GetMapping("/reallydelete/{id}")
		public String reallyDeleteStudent(@ModelAttribute StudentForm form
									,Model model) { 
			model.addAttribute("student",studentService.findOneService(form.getId()));
			return "student/reallydeletestudent";
		}
	//formで受け取りvalidetedしてerrorがなければぬける
		@GetMapping("/delete")
		public String deleteStudent(Integer id
				,RedirectAttributes attributes) {
			studentService.deleteStudent(id);
			attributes.addFlashAttribute("message","生徒情報を削除しました");
			return "redirect:/students";
			}
}
