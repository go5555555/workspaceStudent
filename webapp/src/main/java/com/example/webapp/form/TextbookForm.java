package com.example.webapp.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextbookForm {
	
	private Integer id;
	
	private String title;
	
	private String oldTitle;
	
	private Integer subjectId;
	
	private String subjectName;
	
	private Boolean isNewText;

}
