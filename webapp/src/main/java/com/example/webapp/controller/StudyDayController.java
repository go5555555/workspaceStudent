package com.example.webapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.form.DayForm;
import com.example.webapp.helper.StudyDayHelper;
import com.example.webapp.helper.StudyDetailHelper;
import com.example.webapp.service.StudyDayService;
import com.example.webapp.view.StudentPersonalInfoView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/days")
@RequiredArgsConstructor
public class StudyDayController {

	//DIしてる
	private final StudyDayService studyDayService;
	private final StudyDayHelper studyDayHelper;
	private final StudyDetailHelper studyDetailHelper;

	//生徒ごとに持ってる学習日を選択する画面へ遷移
	@GetMapping("/{studentId}")
	public String detail(@PathVariable Integer studentId, Model model, RedirectAttributes attributes) {
		List<StudentPersonalInfoView> view = studyDayService.findByIdStudent(studentId);
		//新規で登録した生徒の学習日は未登録になるのでその時の処理
		if (view == null || view.isEmpty() || view.stream().allMatch(v -> v.getStudyDay() == null)) {
			//未登録の時はentityから生徒のID、名前、学年だけ取得してる。
			model.addAttribute("student", studyDayService.findByIdNewStudent(studentId));
			return "student/dayselect";

		} //else if(view != null && !view.isEmpty()) {
		model.addAttribute("students", view);
		model.addAttribute("student", view.get(0));
		//listをstream型にして、StudyDayだけ取り出して、NULLを排除、
		//distinctで重複削除、昇順に並べ替え、リスト化をしてる
		List<LocalDate> uniqueStudyDay = studyDayHelper.distinctDay(view);
		model.addAttribute("uniqueStudyDayList", uniqueStudyDay);
		return "student/dayselect";
	}

	//日付登録の画面へ遷移
	@GetMapping("/form")
	public String newDay(@ModelAttribute DayForm form) {
		//trueだと新規登録画面になる
		form.setIsNewDay(true);
		//日付登録のデフォルト選択に今日の日付を表示させる
		form.setStudyDay(LocalDate.now());
		return "student/dayform";
	}

	//フォームから登録される日付を取得
	@PostMapping("/save")
	public String create(@Validated DayForm form, BindingResult bindingResult, RedirectAttributes attributes) {
		//error持ってたら新規登録画面へ戻る
		if (bindingResult.hasErrors()) {
			form.setIsNewDay(true);
			return "student/dayform";
		}
		//errorじゃなかったら、
		//entityに生徒IDと学習日をセット(DBに保存）するメソッドを呼び出す
		//StudyDetail entity = studentDayService.toEntityFromForm(form);
		studyDayService.insertDay(form);
		attributes.addFlashAttribute("message", "新しい日付が登録されました");
		return "redirect:/days/" + form.getStudentId();
	}

	@GetMapping("/form/{studentId}")
	public String changeDay(@ModelAttribute DayForm form) {
		//trueだと新規登録画面になる
		form.setIsNewDay(false);
		//日付登録のデフォルト選択に今日の日付を表示させる
		form.setStudyDay(LocalDate.now());
		return "student/dayform";
	}

	@PostMapping("/update")
	public String updateDay(@Validated DayForm form, Model model, BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors() || form.getStudentName().isEmpty() && form.getGrade().equals(0)) {
			form.setIsNewDay(false);
			attributes.addFlashAttribute("errorMessage", "何も更新されてないよ");
			return "redirect:/days/form/" + form.getId();
		}
		//更新画面で名前と学年のどちらかが未入力の場合、未入力にnullを入れる
		if (studyDayHelper.sameCheck(form)) {
			form.setIsNewDay(false);
			form.setStudyDay(LocalDate.now());
			model.addAttribute("dayForm", form);
			model.addAttribute("message", "更新前と日付が同じ");
			return "student/dayform";
		}
		//整形したデータを送って更新。DBでnullだった場合は更新しない処理にしてる
		studyDayService.updateDay(form);
		studyDetailHelper.trainingdetail(form.getStudentId(), form.getStudyDay(), model);
		model.addAttribute("messageday", "日付を更新しました");
		return "student/training";
	}

	//日付情報削除の確認画面へ遷移
	@GetMapping("/reallydelete")
	public String reallyDeleteStudent(@ModelAttribute DayForm form) {
		return "student/reallydeleteday";
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@GetMapping("/delete")
	public String deleteStudent(Integer studentId
			,LocalDate studyDay
			,Model model) {
		studyDayService.deleteDay(studentId,studyDay);
		List<StudentPersonalInfoView> view = studyDayService.findByIdStudent(studentId);
		//新規で登録した生徒の学習日は未登録になるのでその時の処理
		if (view == null || view.isEmpty() || view.stream().allMatch(v -> v.getStudyDay() == null)) {
			//未登録の時はentityから生徒のID、名前、学年だけ取得してる。
			model.addAttribute("student", studyDayService.findByIdNewStudent(studentId));
			return "student/dayselect";

		} //else if(view != null && !view.isEmpty()) {
		model.addAttribute("students", view);
		model.addAttribute("student", view.get(0));
		//listをstream型にして、StudyDayだけ取り出して、NULLを排除、
		//distinctで重複削除、昇順に並べ替え、リスト化をしてる
		List<LocalDate> uniqueStudyDay = studyDayHelper.distinctDay(view);
		model.addAttribute("uniqueStudyDayList", uniqueStudyDay);
		model.addAttribute("message","学習日を削除しました");
		return "student/dayselect";
	}

}
