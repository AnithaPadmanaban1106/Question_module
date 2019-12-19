package com.revature.revaturequestion.dto;

import java.util.List;

import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.QuestionType;

import lombok.Data;
@Data
public class QuestionDTO {


	

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
	
	private List<Answer> answer;
	
	
	
}
