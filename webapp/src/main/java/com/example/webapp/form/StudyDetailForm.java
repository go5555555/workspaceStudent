package com.example.webapp.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyDetailForm {
	private Integer id;

	private Integer studentId;

	//@NotBlank(message = "氏名は必須です")
	private String studentName;

	//@NotNull(message = "学年は必須です")
	private Integer grade;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate studyDay;

	private Integer subjectId;

	private String subjectName;
	//科目と参考書は選択必須にする（参考書使ってない時はその他）
	private Integer textbookId;

	private Integer oldTextbookId;

	private String title;

	private String pages;
	
	private String oldPages;

	private String memo;
	
	private String oldMemo;

	private Boolean isNewDetail;
}
