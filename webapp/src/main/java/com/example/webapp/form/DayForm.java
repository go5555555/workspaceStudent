package com.example.webapp.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayForm {
	
	private Integer id;
	
	private Integer studentId;

	//@NotBlank(message = "氏名は必須です")
	private String studentName;
	//@NotNull(message = "学年は必須です")
	private Integer grade;
	//@NotNull(message = "学習日は必須です")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate studyDay;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate oldStudyDay;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private Boolean isNewDay;
}
