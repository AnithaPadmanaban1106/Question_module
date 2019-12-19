package com.revature.revaturequestion.model;



import lombok.Data;

@Data

public class QuestionMatching {
	private Integer id;
	private Integer questionId;
	private Integer answerId;
	private String question;
	private Boolean isSticky;
}
