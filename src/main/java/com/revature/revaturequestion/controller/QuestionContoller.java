package com.revature.revaturequestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.revature.revaturequestion.dto.AnswerDTO;
import com.revature.revaturequestion.dto.Message;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.exception.ServiceException;
import com.revature.revaturequestion.model.Question;
import com.revature.revaturequestion.service.QuestionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("question")
public class QuestionContoller {
	@Autowired
	QuestionService questionService;


	@PostMapping()
	@ApiOperation("CreatequestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questionAnswerDTO)
			 {
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.saveQuestionAnswer(questionAnswerDTO);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		
		/*
		 * catch (ValidatorException e) { errorMessage = e.getMessage(); return new
		 * ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST); }
		 */
	}
	
	

	
	
	@DeleteMapping()
	@ApiOperation("DeleteQuestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> deleteQuestion(@RequestParam("questionId") int questionId)
			{
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.deleteQuestion(questionId);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		} 
	}

	
	
	@GetMapping()
	@ApiOperation("ListAllQuestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> listAll(@RequestParam("status") Boolean status )  {
		String errorMessage = null;

		List<Question> result = null;
		try {

			result = questionService.listAll(status);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}

	
	
	
	@GetMapping("/status/update")
	@ApiOperation("ActivateDeactiveQuestionApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Failure") })
	public ResponseEntity<?> ActiveDeactiveQuestion(@RequestParam("questionId") int questionId,@RequestParam("status") Boolean status )  {
		String errorMessage = null;

		Boolean result = false;
		try {

			result = questionService.updateQuestion(questionId, status);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
}
