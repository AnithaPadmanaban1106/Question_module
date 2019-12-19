package com.revature.revaturequestion.dao;

import java.util.List;

import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.DBException;
import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.Question;

public interface QuestionDAO {

	public boolean saveQuestionAnswer(QuestionDTO questionDTO) throws DBException;

	public boolean deleteQuestion(int questionId) throws DBException;

	public boolean updateQuestion(int questionId, Boolean status) throws DBException;

	public boolean update(Question question, Answer answer) throws DBException;

	public List<Question> listAllQuestions(Boolean status) throws DBException;

}
