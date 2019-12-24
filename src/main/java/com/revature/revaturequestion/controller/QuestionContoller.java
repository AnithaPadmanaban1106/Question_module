package com.revature.revaturequestion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revaturequestion.dao.QuestionDAOImpl;
import com.revature.revaturequestion.dto.AnswerDTO;
import com.revature.revaturequestion.dto.Message;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.ServiceException;
import com.revature.revaturequestion.exception.ValidatorException;
import com.revature.revaturequestion.model.Question;
import com.revature.revaturequestion.service.QuestionService;
import com.revature.revaturequestion.validator.QuestionValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("question")
public class QuestionContoller {

	QuestionService questionService = new QuestionService(new QuestionDAOImpl(), new QuestionValidator());

	@PostMapping()
	@ApiOperation("CreatequestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questionAnswerDTO) {
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.saveQuestionAnswer(questionAnswerDTO);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} 
		
		catch (ServiceException  e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		catch ( ValidatorException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		

	}

	@DeleteMapping()
	@ApiOperation("DeleteQuestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> deleteQuestion(@RequestParam("questionId") int questionId) {
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.deleteQuestion(questionId);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	@GetMapping()
	@ApiOperation("ListAllQuestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> listAll(@RequestParam("status") Boolean status) {
		String errorMessage = null;

		List<Question> result = null;
		try {

			result = questionService.listAll(status);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			Message message= new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@GetMapping("/status/update")
	@ApiOperation("ActivateDeactiveQuestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> ActiveDeactiveQuestion(@RequestParam("questionId") int questionId,
			@RequestParam("status") Boolean status) {
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.updateQuestion(questionId, status);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	@PutMapping
	@ApiOperation("UpdateApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> updateQuestion(@RequestBody AnswerDTO answerDTO) {
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.update(answerDTO);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
