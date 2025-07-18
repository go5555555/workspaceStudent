package com.example.webapp.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectForm {

	private Integer id; 
	
	private String subjectName;
	
	private String oldSubjectName;
	
	private Boolean isNewSub; 
}
