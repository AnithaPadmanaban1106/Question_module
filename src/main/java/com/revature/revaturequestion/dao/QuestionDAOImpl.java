package com.revature.revaturequestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revature.revaturequestion.connection.ConnectionUtil;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.DBException;
import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.Question;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
	// @Autowired
	// DataSource datasource;
	Connection con = null;
	PreparedStatement pst = null;
	
	public boolean saveQuestionAnswer(QuestionDTO questionDTO) throws DBException {
		Boolean result = false;
		Savepoint questionAnswer = null;
		try {
			con = ConnectionUtil.getConnection();
			con.setAutoCommit(false);
			questionAnswer = con.setSavepoint("savepoint");
			String sqlQuestion = "insert into questions(title,question_type,content,category_id,tag,level_id,skill_points,score,duration,status,is_import)values(?,?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sqlQuestion);
			pst.setString(1, questionDTO.getTitle());
			pst.setString(2, questionDTO.getQuestionType().toString());
			pst.setString(3, questionDTO.getContent());
			pst.setInt(4, questionDTO.getCategoryId());
			pst.setString(5, questionDTO.getTag());
			pst.setInt(6, questionDTO.getLevelId());
			pst.setInt(7, questionDTO.getSkillPoints());
			pst.setInt(8, questionDTO.getScore());
			pst.setString(9, questionDTO.getDuration());
			pst.setBoolean(10, questionDTO.getStatus());
			pst.setBoolean(11, questionDTO.getIsImported());
			pst.executeUpdate();

			
			String selectQuery = "select last_insert_id()";
			pst = con.prepareStatement(selectQuery);

			ResultSet rs = pst.executeQuery();
			int questionId = 0;
			if (rs.next()) {

				questionId = rs.getInt("last_insert_id()");
			}

			List<Answer> answerList = questionDTO.getAnswer();
			String answerDetail = "";
			int count = 0;
			for (Answer answers : answerList) {
				answerDetail = answerDetail + "(" + questionId + "," + "\"" + answers.getOption() + "\"" + ","
						+ answers.getIsRightAnswer() + " ," + "\"" + answers.getRightAnswerExplanation() + "\"" + ","
						+ answers.getIsStricky() + "," + answers.getGrading() + ")";
				count++;

				if (count < answerList.size()) {
					answerDetail = answerDetail + ",";
				}

			}
			String sqlMultiple = "insert into question_answers(question_id,`option`,is_right_answer,right_ans_explanation,is_sticky,grading)values"
					+ answerDetail;
			System.out.println("sqlMultiple" + sqlMultiple);
			pst = con.prepareStatement(sqlMultiple);
			int check = pst.executeUpdate();
				
		
			/*
			 * String selectAnswerId = "select last_insert_id()"; pst =
			 * con.prepareStatement(selectAnswerId);
			 * 
			 * ResultSet resultSet = pst.executeQuery(); int answerId = 0; if
			 * (resultSet.next()) {
			 * 
			 * answerId = rs.getInt("last_insert_id()");
			 * 
			 * } System.out.println("Answer ID================>"+answerId);
			 * 
			 * if(questionDTO.getQuestionType().toString()=="Matching") { String
			 * sql="insert into question_matchings(question_id,answer_id,question,is_sticky)values(?,?,?,?)"
			 * ; pst = con.prepareStatement(sql); pst.setInt(1,questionId); pst.setInt(2,
			 * answerId); pst.setString(3, questionDTO.getQuestion()); pst.setBoolean(4,
			 * questionDTO.getIsSticky()); pst.executeUpdate();
			 * 
			 * }
			 */
			
			

			con.commit();
			if (check == 1) {
				result = true;
			}
		} catch (SQLException e) {
			try {
				con.rollback(questionAnswer);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Unable to save Question");
		} finally {
			try {
				pst.close();
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean deleteQuestion(int questionId) throws DBException {
		Boolean result = false;
		Savepoint deleteQuestion = null;

		try {
			con = ConnectionUtil.getConnection();

			con.setAutoCommit(false);
			deleteQuestion = con.setSavepoint("savepoint");

			String query = "delete from question_answers where question_id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, questionId);
			pst.executeUpdate();

			String sql = "delete from questions where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, questionId);
			int check = pst.executeUpdate();
			if (check == 1) {
				result = true;
			}
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback(deleteQuestion);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Unable to delete Question");
		} finally {
			try {
				pst.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("result" + result);
		return result;
	}

	public boolean updateQuestion(int questionId, Boolean status) throws DBException {
		Boolean result = false;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "update questions set `status`=? where id=?";
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, status);
			pst.setInt(2, questionId);
			int check = pst.executeUpdate();
			if (check == 1) {
				result = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DBException("Unable to update Question");

		} finally {
			try {
				pst.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Question> listAllQuestions(Boolean status) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		List<Question> question = new ArrayList<Question>();
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select q.title,q.tag,c.name,ql.name from questions q,categories c,question_levels ql where q.category_id=c.id and q.level_id=ql.id and q.status=?";
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, status);
			ResultSet rs = pst.executeQuery();
			Question value = null;
			while (rs.next()) {
				value = new Question();
				value = toRows(rs);
				question.add(value);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to view Questions");

		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("value" + question);
		return question;
	}

	public Question toRows(ResultSet rs) {
		Question value = new Question();
		try {
			String title = rs.getString("q.title");
			String tag = rs.getString("q.tag");
			// question.setQuestionType(rs.getString("question_type"));
			String categoryName = rs.getString("c.name");
			String levelName = rs.getString("ql.name");

			value.setTitle(title);
			value.setCategoryName(categoryName);
			value.setLevelName(levelName);
			value.setTag(tag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public boolean update(Question question, Answer answer) throws DBException {
		Boolean result = false;
		try {
			con = ConnectionUtil.getConnection();
			String sqlQuestion = "update questions set title=?,content=?,category_id=?,level_id=?,tag=?,skill_points=?,score=?,duration=? where id=?";
			pst = con.prepareStatement(sqlQuestion);
			pst.setString(1, question.getTitle());
			pst.setString(2, question.getContent());
			pst.setInt(3, question.getCategoryId());
			pst.setInt(4, question.getLevelId());
			pst.setString(5, question.getTag());
			pst.setInt(6, question.getSkillPoints());
			pst.setInt(7, question.getScore());
			pst.setString(8, question.getDuration());
			pst.setInt(9, question.getQuestionId());

			pst.executeUpdate();

			String sqlAnswer = "update question_answers set `option`=?,is_right_answer=?,right_ans_explanation=?,is_sticky=? where id=?";
			pst = con.prepareStatement(sqlAnswer);
			pst.setString(1, answer.getOption());
			pst.setBoolean(2, answer.getIsRightAnswer());
			pst.setString(3, answer.getRightAnswerExplanation());
			pst.setBoolean(4, answer.getIsStricky());
			pst.setInt(5, answer.getId());
			int check = pst.executeUpdate();
			if (check == 1) {
				result = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DBException("Connection error");

		} finally {
			try {
				pst.close();
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
