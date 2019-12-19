package com.revature.revaturequestion.model;

import lombok.Data;

@Data
public class Question {

	

	private Integer questionId;

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
	
	private String categoryName;
	private String levelName;

	}
