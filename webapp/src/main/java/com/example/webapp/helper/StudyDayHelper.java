package com.example.webapp.helper;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.example.webapp.form.DayForm;
import com.example.webapp.view.StudentPersonalInfoView;

@Component
//@RequiredArgsConstructor
public class StudyDayHelper {
	public boolean sameCheck(DayForm form) {
		return form.getOldStudyDay().equals(form.getStudyDay());
	}
	
	public List<LocalDate> distinctDay(List<StudentPersonalInfoView> view ) {
		return view.stream()
		.map(StudentPersonalInfoView::getStudyDay)
		.filter(Objects::nonNull)
		.distinct()
		.sorted(Comparator.reverseOrder())
		.toList();
	}
}
