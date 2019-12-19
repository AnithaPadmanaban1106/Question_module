package com.revature.revaturequestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.revaturequestion.dao.QuestionDAOImpl;
import com.revature.revaturequestion.dto.AnswerDTO;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.DBException;
import com.revature.revaturequestion.exception.ServiceException;
import com.revature.revaturequestion.exception.ValidatorException;
import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.Question;
import com.revature.revaturequestion.validator.QuestionValidator;



@Service
public class QuestionService {
	@Autowired
	QuestionDAOImpl questionDAOImpl;
	
	@Autowired
	QuestionValidator questionValidator;
	
	QuestionService(QuestionDAOImpl question,QuestionValidator validator)
	{
		this.questionDAOImpl=question;
		this.questionValidator=validator;
	}
	
	

	public boolean saveQuestionAnswer(QuestionDTO questionDTO) throws ServiceException{

		Boolean result;
		try {

				questionValidator.questionValidate(questionDTO);
			
				
				
			result = questionDAOImpl.saveQuestionAnswer(questionDTO);
		} catch (DBException | ValidatorException  e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());

		}
		return result;

	}

	public boolean deleteQuestion(int questionId) throws  ServiceException {

		Boolean result;
		try {
		
		
			result = questionDAOImpl.deleteQuestion(questionId);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return result;
	}
	
	
	
	

	public boolean updateQuestion(int questionId, Boolean status) throws ServiceException

	{
		Boolean result = null;
		try {
			
				//questionValidator.updateQuestionValidate(questionId, status);
		
			result = questionDAOImpl.updateQuestion(questionId, status);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return result;

	}
	
	
	
	public List<Question> listAll(Boolean status) throws ServiceException {
		List<Question> question = null;
		try {

			question = questionDAOImpl.listAllQuestions(status);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		System.out.println("list" + question);
		return question;
	}
	
	

	public boolean update(AnswerDTO answerDTO) throws ServiceException

	{
		Boolean result = null;
		try {
			Question question =new Question();
			question.setTitle(answerDTO.getTitle());
			question.setContent(answerDTO.getContent());
			question.setCategoryId(answerDTO.getCategoryId());
			question.setTag(answerDTO.getTag());
			question.setLevelId(answerDTO.getLevelId());
			question.setSkillPoints(answerDTO.getSkillPoints());
			question.setScore(answerDTO.getScore());
			question.setDuration(answerDTO.getDuration());
			
			Answer answer = new Answer();
			answer.setOption(answerDTO.getOption());
			answer.setIsRightAnswer(answerDTO.getIsRightAnswer());
			answer.setRightAnswerExplanation(answerDTO.getRightAnswerExplanation());
			answer.setIsStricky(answerDTO.getIsStricky());
			
			
			

			// questionValidator.updateQuestionValidate(question, status);
			result = questionDAOImpl.update(question,answer);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to update question");
		}
		return result;

	}

}
