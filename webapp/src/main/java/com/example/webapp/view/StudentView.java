package com.example.webapp.view;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentView {
	
	private Integer id;
	
	private String studentName;
	
	private Integer grade;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
