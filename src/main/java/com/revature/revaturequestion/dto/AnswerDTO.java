package com.revature.revaturequestion.dto;

import com.revature.revaturequestion.model.QuestionType;

import lombok.Data;


@Data
public class AnswerDTO {
	private Integer id;
	private Integer questionId;
	private String option;
	private Boolean isRightAnswer;
	private String rightAnswerExplanation;
	private Integer grading;
	private Boolean isStricky;
	
	private String title;

	private QuestionType questionType;

	private String content;

	private Integer categoryId;

	private String tag;

	private Integer levelId;

	private Integer skillPoints;

	private Integer score;

	private String duration;

	private Boolean status;

	private Boolean isImported;

	}
