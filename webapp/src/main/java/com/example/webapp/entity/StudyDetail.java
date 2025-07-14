package com.example.webapp.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyDetail {
	
	private Integer id;
	
	private Integer studentId;
	
	private Integer textBookId;
	//学習状況に登録してある参考書を別の参考書に書き換える時に検索条件として使う
	private Integer oldTextBookId;
	//複数登録したい
	//private List<LocalDate> training_date;
	private LocalDate studyDay;
	//更新の条件検索に使うだけDBにはない
	private LocalDate oldStudyDay;
	//複数登録したい
	//private List<String> subject;
	private String pages;
	
	private String memo; 

}
