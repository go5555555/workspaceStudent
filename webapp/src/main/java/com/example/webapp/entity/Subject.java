package com.example.webapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
	
	private Integer id; 
	
	private String subjectName;
	
	private String oldSubjectName;

}
