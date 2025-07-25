package com.example.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneStudentDTO {
	
	String message;
	
	private Integer id;
	
	private String studentName;
	
	private Integer grade;
	
	private Boolean IsNew; 
	
	public OneStudentDTO(String message,Integer id,String studentName,Integer grade){
		this.message = message;
		this.id = id;
		this.studentName = studentName;
		this.grade = grade;
	}

}
