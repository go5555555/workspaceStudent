package com.example.webapp.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.dto.ApiResponse;
import com.example.webapp.dto.StudentDTO;
import com.example.webapp.form.StudentForm;
import com.example.webapp.helper.StudentHelper;
import com.example.webapp.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class RestStudentController {

	private final StudentService studentService;
	private final StudentHelper studentHelper;

	//セキュリティ画面からリストに遷移するように作る
	//生徒一覧を表示する
	@GetMapping
	public ResponseEntity<StudentDTO> studentlist() {
		StudentDTO sd = studentService.buildStudentListWithMessage("リストが生成されました");
		return ResponseEntity.ok(sd);
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@PostMapping
	public ResponseEntity<ApiResponse<?>> create(@RequestBody @Validated StudentForm form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			//getFielsErrorsで１つ以上のerrorを取得。
			//bindingResultにerrorフィールドがあるからそれを引数にしてるラムダ式
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			ApiResponse<Map<String, String>> errorResponse = new ApiResponse<>(false, errors);
			return ResponseEntity.badRequest().body(errorResponse);
		}
		//formをentityに保存するためのメソッドを呼ぶ
		studentService.insertStudent(form);
		StudentDTO sd = studentService.buildStudentListWithMessage("新しい生徒が登録されました");
		ApiResponse<StudentDTO> successResponse = new ApiResponse<>(true, sd);
		return ResponseEntity.ok(successResponse);
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@PutMapping
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentForm form) {
		if (form.getStudentName().isEmpty() &&
				(form.getGrade() == null || form.getGrade() == 0)) {
			//更新画面で名前と学年のどっちも未入力の場合、未入力にnullを入れる
			StudentDTO sd = studentService.buildStudentListWithMessage("更新されてません");
			return ResponseEntity.ok(sd);
		}
		//どちらかしか更新しなかった場合とどっちも更新した場合
		studentHelper.replaceEmptyFieldsWithNull(form);
		//整形したデータを送って更新。DBでnullだった場合は更新しない処理にしてる
		studentService.updateStudent(form);
		StudentDTO sd = studentService.buildStudentListWithMessage("生徒情報を更新しました");
		return ResponseEntity.ok(sd);
	}

	//formで受け取りvalidetedしてerrorがなければぬける
	@DeleteMapping("/{id}")
	public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer id) {
		studentService.deleteStudent(id);
		StudentDTO sd = studentService.buildStudentListWithMessage("生徒情報が削除されました");
		return ResponseEntity.ok(sd);
	}
}