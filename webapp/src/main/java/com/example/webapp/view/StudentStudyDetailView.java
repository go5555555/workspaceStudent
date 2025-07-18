package com.example.webapp.view;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentStudyDetailView {
	private Integer id;
	
	private Integer studentId;
	
	//@NotBlank(message = "氏名は必須です")
	private String studentName;
	//@NotNull(message = "学年は必須です")
	private Integer grade;
	
	private LocalDate studyDay;
	
	private Integer subjectId;
	
	private String subjectName;
	
	private Integer textbookId;
	
	private String title;
	
	private String pages;
	
	private String memo; 
	
	private Boolean isNewDetail;
}
