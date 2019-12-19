package com.revature.revaturequestion.model;

import java.util.List;

import lombok.Data;

@Data

public class Answer {

	
	private Integer id;
	private Integer questionId;
	private String option;
	private Boolean isRightAnswer;
	private String rightAnswerExplanation;
	private Integer grading;
	private Boolean isStricky;
	
}
