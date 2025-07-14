package com.example.webapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Textbook {
	
	private Integer id;
	
	private String title;
	
	//テーブルにはない。更新処理用
	private String oldTitle;
	
	private Integer subjectId;

}
