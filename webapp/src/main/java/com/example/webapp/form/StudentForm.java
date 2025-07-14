//生徒の新規登録、更新用
package com.example.webapp.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentForm {
	private Integer id;
	//@NotBlank(message = "氏名は必須です")
	private String studentName;
	//@NotNull(message = "学年は必須です") 
	private Integer grade;
	private Boolean isNew;
	
}
