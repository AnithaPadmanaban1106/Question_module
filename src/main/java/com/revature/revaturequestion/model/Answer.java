package com.revature.revaturequestion.model;


import lombok.Data;

@Data

public class Answer {

	
	private Integer id;
	private Integer questionId;
	private String option;
	private Boolean isRightAnswer;
	private String rightAnswerExplanation;
	private Integer grading;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Boolean getRightAnswer() {
		return isRightAnswer;
	}

	public void setRightAnswer(Boolean rightAnswer) {
		isRightAnswer = rightAnswer;
	}

	public String getRightAnswerExplanation() {
		return rightAnswerExplanation;
	}

	public void setRightAnswerExplanation(String rightAnswerExplanation) {
		this.rightAnswerExplanation = rightAnswerExplanation;
	}

	public Integer getGrading() {
		return grading;
	}

	public void setGrading(Integer grading) {
		this.grading = grading;
	}



}
