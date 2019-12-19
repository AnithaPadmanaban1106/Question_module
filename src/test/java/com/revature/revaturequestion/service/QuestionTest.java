package com.revature.revaturequestion.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.revature.revaturequestion.dao.QuestionDAOImpl;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.ServiceException;
import com.revature.revaturequestion.exception.ValidatorException;
import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.Question;
import com.revature.revaturequestion.model.QuestionType;
import com.revature.revaturequestion.validator.QuestionValidator;

//@RunWith(SpringRunner.class)
public class QuestionTest {
	QuestionService questionService = new QuestionService(new QuestionDAOImpl(), new QuestionValidator());

	// DataSource dataSource;

	// ListAllQuestionService listAllQuestionService = new
	// ListAllQuestionService(new ListAllQuestionDAOImpl(dataSource));

	// ListAllQuestionDAOImpl obj=new ListAllQuestionDAOImpl();

	@Test
	public void validDeleteQuestionTest() throws ValidatorException, ServiceException {

		int questionId = 68;
		Boolean result = false;
		result = questionService.deleteQuestion(questionId);
		assertTrue(result);
	}

	@Test
	public void inValidDeleteQuestionTest() throws ValidatorException, ServiceException {
		int questionId = 0;
		Boolean result = false;
		result = questionService.deleteQuestion(questionId);
		assertFalse(result);

	}

	@Test
	public void validListAllActiveQuestionTest() throws ServiceException {
		Boolean status = true;
		List<Question> question = null;
		question = questionService.listAll(status);
		assertNotNull(question);
	}
	
	
	

	@Test
	public void updateQuestionDeactiveValidTest() throws ServiceException {

		Boolean status = false;
		int questionId = 22;
		Boolean question = false;
		question = questionService.updateQuestion(questionId, status);
		assertTrue(question);

	}

	@Test
	public void updateQuestionActiveValidTest() throws ServiceException {

		Boolean status = true;
		int questionId = 22;
		Boolean question = false;
		question = questionService.updateQuestion(questionId, status);
		assertTrue(question);

	}
	
	
	
	@Test
	public void addQuestionValidTest() throws ValidatorException, ServiceException, SQLException {

		Boolean result = false;

		String title = "Devops";

		QuestionType questionType = QuestionType.MultipleChoice;

		String content = "what is deveops";

		int categoryId = 3;

		String tag = "deveops";

		int levelId = 2;

		int skillPoints = 5;

		int score = 6;

		String duration = "11:23:21";

		Boolean status = true;

		Boolean isImported = true;

		String option = "development and integration";
		Boolean isRightAnswer = true;
		String rightAnswerExplanation = "right answer";
		int grading = 6;
		Boolean isStricky = true;

		Answer answer = new Answer();
		answer.setOption(option);
		answer.setIsRightAnswer(isRightAnswer);
		answer.setRightAnswerExplanation(rightAnswerExplanation);
		answer.setGrading(grading);
		answer.setIsStricky(isStricky);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle(title);
		questionDTO.setQuestionType(questionType);
		questionDTO.setContent(content);
		questionDTO.setCategoryId(categoryId);
		questionDTO.setTag(tag);
		questionDTO.setLevelId(levelId);
		questionDTO.setSkillPoints(skillPoints);
		questionDTO.setScore(score);
		questionDTO.setDuration(duration);
		questionDTO.setStatus(status);
		questionDTO.setIsImported(isImported);

		result = questionService.saveQuestionAnswer(questionDTO);
		assertTrue(result);

	}

	@Test(expected = ServiceException.class)
	public void addquestionInvalidTest() throws ServiceException {

		String title = "#";

		QuestionType questionType = QuestionType.MultipleChoice;

		String content = "whatisdeveops";

		int categoryId = 3;

		String tag = "deveops";

		int levelId = 2;

		int skillPoints = 5;

		int score = 6;

		String duration = "11:23:21";

		Boolean status = true;

		Boolean isImported = true;

		int questionId = 23;
		String option = "developmentandintegration";
		Boolean isRightAnswer = true;
		String rightAnswerExplanation = "rightanswer";
		int grading = 6;
		Boolean isStricky = true;

		Answer answer = new Answer();
		answer.setQuestionId(questionId);
		answer.setOption(option);
		answer.setIsRightAnswer(isRightAnswer);
		answer.setRightAnswerExplanation(rightAnswerExplanation);
		answer.setGrading(grading);
		answer.setIsStricky(isStricky);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle(title);
		questionDTO.setQuestionType(questionType);
		questionDTO.setContent(content);
		questionDTO.setCategoryId(categoryId);
		questionDTO.setTag(tag);
		questionDTO.setLevelId(levelId);
		questionDTO.setSkillPoints(skillPoints);
		questionDTO.setScore(score);
		questionDTO.setDuration(duration);
		questionDTO.setStatus(status);
		questionDTO.setIsImported(isImported);

		questionService.saveQuestionAnswer(questionDTO);

	}

	@Test(expected = ServiceException.class)
	public void invalidCategoryIdTest() throws ServiceException {
		Answer answer = new Answer();
		answer.setOption("someanswer");
		answer.setIsRightAnswer(true);
		answer.setRightAnswerExplanation("Explanation");
		answer.setGrading(2);
		answer.setIsStricky(true);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle("Jquery");
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setContent("some");
		questionDTO.setCategoryId(0);
		questionDTO.setTag("Query");
		questionDTO.setLevelId(1);
		questionDTO.setSkillPoints(5);
		questionDTO.setScore(4);
		questionDTO.setDuration("2:3:2");
		questionDTO.setStatus(true);
		questionDTO.setIsImported(true);

		questionService.saveQuestionAnswer(questionDTO);

	}

	@Test(expected = ServiceException.class)
	public void invalidTagTest() throws ServiceException {
		Answer answer = new Answer();
		answer.setOption("someanswer");
		answer.setIsRightAnswer(true);
		answer.setRightAnswerExplanation("Explanation");
		answer.setGrading(2);
		answer.setIsStricky(true);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle("Jquery");
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setContent("What is jquery");
		questionDTO.setCategoryId(5);
		questionDTO.setTag("*^&*");
		questionDTO.setLevelId(1);
		questionDTO.setSkillPoints(5);
		questionDTO.setScore(4);
		questionDTO.setDuration("2:3:2");
		questionDTO.setStatus(true);
		questionDTO.setIsImported(true);

		questionService.saveQuestionAnswer(questionDTO);

	}

	@Test(expected = ServiceException.class)
	public void invalidLevelIdTest() throws ServiceException {
		Answer answer = new Answer();
		answer.setOption("someanswer");
		answer.setIsRightAnswer(true);
		answer.setRightAnswerExplanation("Explanation");
		answer.setGrading(2);
		answer.setIsStricky(true);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle("Jquery");
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setContent("what is Json");
		questionDTO.setCategoryId(3);
		questionDTO.setTag("Query");
		questionDTO.setLevelId(0);
		questionDTO.setSkillPoints(5);
		questionDTO.setScore(4);
		questionDTO.setDuration("2:3:2");
		questionDTO.setStatus(true);
		questionDTO.setIsImported(true);

		questionService.saveQuestionAnswer(questionDTO);

	}

	@Test(expected = NullPointerException.class)
	public void invalidSkillPointsTest() throws ServiceException {
		Answer answer = new Answer();
		answer.setOption("someanswer");
		answer.setIsRightAnswer(true);
		answer.setRightAnswerExplanation("Explanation");
		answer.setGrading(2);
		answer.setIsStricky(true);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle("Jquery");
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setContent("what is Json");
		questionDTO.setCategoryId(3);
		questionDTO.setTag("Query");
		questionDTO.setLevelId(2);
		questionDTO.setSkillPoints(null);
		questionDTO.setScore(4);
		questionDTO.setDuration("2:3:2");
		questionDTO.setStatus(true);
		questionDTO.setIsImported(true);

		questionService.saveQuestionAnswer(questionDTO);

	}

	@Test(expected = NullPointerException.class)
	public void invalidScoreTest() throws ServiceException {
		Answer answer = new Answer();
		answer.setOption("someanswer");
		answer.setIsRightAnswer(true);
		answer.setRightAnswerExplanation("Explanation");
		answer.setGrading(2);
		answer.setIsStricky(true);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle("Jquery");
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setContent("what is Json");
		questionDTO.setCategoryId(3);
		questionDTO.setTag("Query");
		questionDTO.setLevelId(3);
		questionDTO.setSkillPoints(5);
		questionDTO.setScore(null);
		questionDTO.setDuration("2:3:2");
		questionDTO.setStatus(true);
		questionDTO.setIsImported(true);

		questionService.saveQuestionAnswer(questionDTO);
	}
	@Test(expected = ServiceException.class)
	public void invalidDurationTest() throws ServiceException {
		Answer answer = new Answer();
		answer.setOption("someanswer");
		answer.setIsRightAnswer(true);
		answer.setRightAnswerExplanation("Explanation");
		answer.setGrading(2);
		answer.setIsStricky(true);
		List<Answer> answerObj = new ArrayList<Answer>();
		answerObj.add(answer);
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setAnswer(answerObj);
		questionDTO.setTitle("Jquery");
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setContent("what is Json");
		questionDTO.setCategoryId(3);
		questionDTO.setTag("Query");
		questionDTO.setLevelId(3);
		questionDTO.setSkillPoints(5);
		questionDTO.setScore(4);
		questionDTO.setDuration(".");
		questionDTO.setStatus(true);
		questionDTO.setIsImported(true);

		questionService.saveQuestionAnswer(questionDTO);

	}


	/*
	 * @Test(expected = ValidatorException.class) public void
	 * updateQuestionActiveInValidTest() throws ServiceException { Boolean status =
	 * true; int questionId = 0;
	 * 
	 * questionService.updateQuestion(questionId, status);
	 * 
	 * }
	 * 
	 * @Test(expected = ServiceException.class) public void
	 * updateQuestionDeactiveInValidTest() throws ServiceException { Boolean status
	 * = false; int questionId = 0; questionService.updateQuestion(questionId,
	 * status); }
	 */
}
