package com.example.webapp.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextbookView {
	
	private Integer id;
	
	private String title;
	
	private Integer subjectId;
	
	private String subjectName;

}
