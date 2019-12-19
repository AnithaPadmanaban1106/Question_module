package com.revature.revaturequestion.dao;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.DBException;
import com.revature.revaturequestion.model.Answer;

@RunWith(MockitoJUnitRunner.class)
public class QuestionMockitoDAO {
	@InjectMocks
	private QuestionDAOImpl questionDAOImpl;
	@Mock
	private DataSource dataSource;
	@Mock
	private PreparedStatement pstmt;
	@Mock
	private Connection con;
	@Mock
	private ResultSet resultSet;

	@Before
	public void setup() throws SQLException {
		when(dataSource.getConnection()).thenReturn(con);
		when(con.prepareStatement(Mockito.anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(Boolean.FALSE);
	}
	
	@Test
	public void testFindAllQuestions() throws DBException {
		questionDAOImpl.listAllQuestions(Mockito.anyBoolean());
	}

	@Test
	public void testDeleteQuestion() throws DBException {
		questionDAOImpl.deleteQuestion(Mockito.anyInt());
	}

	@Test
	public void testAddQuestion() throws DBException, NullPointerException {

		QuestionDTO questionDTO = new QuestionDTO();
		Answer answer = new Answer();
		answer.setOption("CSharp");
		answer.setRightAnswerExplanation("Csharp is ool");

		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);

		questionDTO.setAnswer(answerList);
		questionDTO.setCategoryId(2);
		questionDTO.setTitle("cSharpbasics");

		Boolean result = questionDAOImpl.saveQuestionAnswer(questionDTO);
		assertFalse(result);
	}
}
