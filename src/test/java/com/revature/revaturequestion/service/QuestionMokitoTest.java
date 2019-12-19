package com.revature.revaturequestion.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.revaturequestion.dao.QuestionDAOImpl;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.DBException;
import com.revature.revaturequestion.exception.ServiceException;
import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.Question;
import com.revature.revaturequestion.validator.QuestionValidator;

@RunWith(MockitoJUnitRunner.class)
public class QuestionMokitoTest {

	@InjectMocks
	QuestionService questionService;
	@Mock
	QuestionValidator questionValidator;
	@Mock
	QuestionDAOImpl questionDAOImpl;

	@Test
	public void testListAllQuestions() throws DBException, ServiceException {
		Boolean status = true;
		List<Question> listObject = new ArrayList<Question>();
		Question question = new Question();
		question.setCategoryId(3);
		question.setTitle("some");
		listObject.add(question);
		when(questionDAOImpl.listAllQuestions(status)).thenReturn(listObject);
		List<Question> questionList = questionService.listAll(status);
		assertEquals(questionList, listObject);

	}

	@Test
	public void validTestAddQuestions() throws DBException, ServiceException {

		QuestionDTO questionDTO = new QuestionDTO();

		Answer answer = new Answer();
		answer.setOption("CSharp");
		answer.setRightAnswerExplanation("Csharp is ool");

		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);

		questionDTO.setAnswer(answerList);
		questionDTO.setCategoryId(2);
		questionDTO.setTitle("cSharpbasics");
		Boolean result = false;

		when(questionDAOImpl.saveQuestionAnswer(questionDTO)).thenReturn(true);
		result = questionService.saveQuestionAnswer(questionDTO);
		assertTrue(result);

	}

	@Test(expected = ServiceException.class)
	public void inValidTestAddQuestions() throws ServiceException, DBException {

		QuestionDTO questionDTO = new QuestionDTO();

		Answer answer = new Answer();
		answer.setOption("some");
		answer.setRightAnswerExplanation("This is right Answer");

		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);

		questionDTO.setAnswer(answerList);
		questionDTO.setCategoryId(2);
		questionDTO.setTitle("Java");

		when(questionDAOImpl.saveQuestionAnswer(questionDTO)).thenThrow(DBException.class);
		questionService.saveQuestionAnswer(questionDTO);

	}

	@Test
	public void validDeleteQuestion() throws DBException, ServiceException {
		when(questionDAOImpl.deleteQuestion(Mockito.anyInt())).thenReturn(true);
		Boolean result = questionService.deleteQuestion(Mockito.anyInt());
		assertTrue(result);
	}

	@Test(expected = ServiceException.class)
	public void inValidDeleteQuestion() throws ServiceException, DBException {
		when(questionDAOImpl.deleteQuestion(Mockito.anyInt())).thenThrow(DBException.class);
		questionService.deleteQuestion(Mockito.anyInt());
	}

	@Test
	public void updateQuestionStatusAsActiveValidTest() throws DBException, ServiceException {

		when(questionDAOImpl.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(true);
		Boolean result = questionService.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean());
		assertTrue(result);

	}

	@Test(expected = ServiceException.class)
	public void updateQuestionStatusAsActiveInvalidTest() throws ServiceException, DBException {

		when(questionDAOImpl.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean())).thenThrow(DBException.class);
		questionService.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean());
	}

	@Test
	public void updateQuestionStatusAsInactiveValidTest() throws DBException, ServiceException {

		when(questionDAOImpl.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(true);
		Boolean result = questionService.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean());
		assertTrue(result);

	}

	@Test(expected = ServiceException.class)
	public void updateQuestionStatusAsInactiveInvalidTest() throws ServiceException, DBException {
		when(questionDAOImpl.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean())).thenThrow(DBException.class);
		questionService.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean());
	}
}
