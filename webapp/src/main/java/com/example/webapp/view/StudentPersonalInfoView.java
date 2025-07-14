package com.example.webapp.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//選択した生徒の情報をdayselectに出力するデータを受け取るクラス
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPersonalInfoView {

		private Integer id;
		
		private Integer studentId;

		//@NotBlank(message = "氏名は必須です")
		private String studentName;
		//@NotNull(message = "学年は必須です")
		private Integer grade;
		//@NotNull(message = "学習日は必須です")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate studyDay;
		
		private LocalDateTime createdAt;
		
		private LocalDateTime updatedAt;
		
		private Boolean isNewDay;
}
