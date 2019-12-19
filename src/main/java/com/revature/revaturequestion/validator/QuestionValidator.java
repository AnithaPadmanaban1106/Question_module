package com.revature.revaturequestion.validator;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.revature.revaturequestion.dto.MessageConstant;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.ValidatorException;
import com.revature.revaturequestion.model.Answer;
//import com.revature.revaturequestion.model.QuestionType;

@Component
public class QuestionValidator {

	public void questionValidate(QuestionDTO questionDTO) throws ValidatorException {
	  List<Answer> answer=questionDTO.getAnswer();
	    
		/*
		 * String option = questionDTO.getOption(); String isRightAnswer =
		 * String.valueOf(questionDTO.getIsRightAnswer()); String rightAnswerExplanation
		 * = questionDTO.getRightAnswerExplanation(); String grading =
		 * questionDTO.getGrading();
		 */

		// String isStricky = String.valueOf(questionDTO.getIsStricky());
		String title = questionDTO.getTitle();
		// QuestionType questionType = questionAnswerDTO.getQuestionType();
		String content = questionDTO.getContent();
		String categoryId = Integer.toString(questionDTO.getCategoryId());
		String tag = questionDTO.getTag();
		String levelId = Integer.toString(questionDTO.getLevelId());
		String skillPoints = Integer.toString(questionDTO.getSkillPoints());
		String score = Integer.toString(questionDTO.getScore());
		String duration = questionDTO.getDuration();

		// String status = String.valueOf(questionDTO.getStatus());
		// String isImported = String.valueOf(questionDTO.getIsImported());

		// Pattern validateIsRightAnswer =
		// Pattern.compile(".*[Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee].*");
		Pattern validateRightAnswerExplanation = Pattern.compile(".*[^a-zA-Z0-9 ].*");
		Pattern validateGrading = Pattern.compile(".*[^0-9].*");
		Pattern validateOption = Pattern.compile(".*[^a-zA-Z0-9 ].*");
		// Pattern validateIsStricky =
		// Pattern.compile(".*[Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee].*");
		Pattern validateTitle = Pattern.compile(".*[^a-zA-Z0-9 ].*");
		Pattern validateContent = Pattern.compile(".*[^a-zA-Z0-9? ].*");
		Pattern validateCategoryId = Pattern.compile(".*[^0-9].*");
		Pattern validateTag = Pattern.compile(".*[^a-zA-Z0-9 ].*");
		Pattern validateLevelId = Pattern.compile(".*[^0-9].*");
		Pattern validateSkillPoints = Pattern.compile(".*[^0-9].*");
		Pattern validateScore = Pattern.compile(".*[^0-9].*");
		Pattern validateDuration = Pattern.compile(".*[^0-9:].*");
		// Pattern validateStatus =
		// Pattern.compile(".*[Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee].*");
		// Pattern validateIsImported =
		// Pattern.compile(".*[Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee].*");

		/*
		 * if (option == null || validateOption.matcher(option).matches()) { throw new
		 * ValidatorException(MessageConstant.OPTION); }
		 */

		/*
		 * if (isRightAnswer ==
		 * null||validateIsRightAnswer.matcher(isRightAnswer).matches()) { throw new
		 * ValidatorException(MessageConstant.MESSAGE); }
		 */

		/*
		 * if(rightAnswerExplanation==null||validateRightAnswerExplanation.matcher(
		 * rightAnswerExplanation).matches()) { throw new
		 * ValidatorException(MessageConstant.RIGHTANSWEREXPLANATION); }
		 * 
		 * if(grading==null||validateGrading.matcher(grading).matches()) { throw new
		 * ValidatorException(MessageConstant.GRADING); }
		 */

		/*
		 * if(isStricky==null||validateIsStricky.matcher(isStricky).matches()) { throw
		 * new ValidatorException(MessageConstant.MESSAGE); }
		 */
		if (title == null || validateTitle.matcher(title).matches()) {
			throw new ValidatorException(MessageConstant.TITLE);
		}
		if (content == null || validateContent.matcher(content).matches()) {
			throw new ValidatorException(MessageConstant.CONTENT);
		}
		if (categoryId == null || validateCategoryId.matcher(categoryId).matches()) {
			throw new ValidatorException(MessageConstant.CATEGORYID);
		}
		if (tag == null || validateTag.matcher(tag).matches()) {
			throw new ValidatorException(MessageConstant.TAG);
		}
		if (levelId == null || validateLevelId.matcher(levelId).matches()) {
			throw new ValidatorException(MessageConstant.LEVELID);
		}
		if (skillPoints == null || validateSkillPoints.matcher(skillPoints).matches()) {
			throw new ValidatorException(MessageConstant.SKILLPOINTS);
		}
		if (score == null || validateScore.matcher(score).matches()) {
			throw new ValidatorException(MessageConstant.SCORE);
		}
		if (duration == null || validateDuration.matcher(duration).matches()) {
			throw new ValidatorException(MessageConstant.DURATION);
		}
		/*
		 * if(status==null||(validateStatus.matcher(status).matches())) { throw new
		 * ValidatorException(MessageConstant.MESSAGE); }
		 * if(isImported==null||validateIsImported.matcher(isImported).matches()) {
		 * throw new ValidatorException(MessageConstant.MESSAGE); }
		 */
	}

	/*
	 * public void deleteQuestionValidate(int questionId) throws ValidatorException
	 * {
	 * 
	 * Pattern pattern = Pattern.compile(".*[^0-9].*"); if
	 * (pattern.matcher(questionId).matches()) { throw new
	 * ValidatorException(MessageConstant.QUESTIONID); }
	 * 
	 * }
	 */

	public void updateQuestionValidate(String questionId, String status) throws ValidatorException {

		Pattern pattern = Pattern.compile(".*[^0-9].*");
		if (questionId == null || pattern.matcher(questionId).matches() || status == null
				|| pattern.matcher(status).matches()) {
			throw new ValidatorException(MessageConstant.Message);
		}

	}

}
