package com.revature.revaturequestion.doc;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revaturequestion.controller.QuestionContoller;
import com.revature.revaturequestion.dto.QuestionDTO;
import com.revature.revaturequestion.model.Answer;
import com.revature.revaturequestion.model.Question;
import com.revature.revaturequestion.model.QuestionType;
import com.revature.revaturequestion.service.QuestionService;

@RunWith(MockitoJUnitRunner.class)
public class GetQuestionTestCase {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	@InjectMocks
	QuestionContoller questionController;
	@Mock
	QuestionService questionService;

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(questionController)
				.apply(documentationConfiguration(this.restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(9000))
				.build();
		objectMapper = new ObjectMapper();
	}

	@Test

	public void testFindAllQuestions() throws Exception {
		List<Question> listObject = new ArrayList<Question>();
		Question question = new Question();
		question.setCategoryId(3);
		question.setTitle("Java Basics");
		question.setCategoryName("Java");
		question.setContent("What is Serializable");
		question.setDuration("1:1:30");
		question.setIsImported(true);
		question.setLevelId(3);
		question.setLevelName("Level300");
		question.setQuestionId(1);
		question.setQuestionType(QuestionType.BestChoice);
		question.setScore(2);
		question.setSkillPoints(1);
		question.setTag("java");
		question.setStatus(true);
		listObject.add(question);
		when(questionService.listAll(Mockito.anyBoolean())).thenReturn(listObject);
		this.mockMvc.perform(get("/question?status=true")).andExpect(status().isOk())
				.andDo(document("question/getquestion",
						preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
						
						requestParameters(
								parameterWithName("status")
				                .description("Get question based on status").attributes(key("required").value(true)))));
						
	}

	@Test

	public void testDeleteQuestions() throws Exception {

		when(questionService.deleteQuestion(Mockito.anyInt())).thenReturn(true);
		this.mockMvc.perform(delete("/question/?questionId=5")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andDo(document("question/deletequestion",
						preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
						requestParameters(
								parameterWithName("questionId")
				                .description("question deletion based on question id").attributes(key("required").value(true)))));
						
	}

	/*
	 * @Test(expected = NullPointerException.class) public void
	 * testInvalidDeleteQuestions() throws Exception {
	 * 
	 * when(questionService.deleteQuestion(Mockito.any())).thenThrow(DBException.
	 * class);
	 * this.mockMvc.perform(delete("/question/?questionId=6")).andExpect(status().
	 * isBadRequest())
	 * .andExpect(content().contentType("application/json")).andDo(document(
	 * "question/deletequestioninvalid", preprocessRequest(prettyPrint()),
	 * preprocessResponse(prettyPrint()))); }
	 */

	
	
	@Test

	public void testUpdateQuestions() throws Exception {
				
		
		when(questionService.updateQuestion(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(true);
		this.mockMvc.perform(get("/question/status/update/?questionId=1&status=false")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andDo(document("question/updatequestionbystatus",
						preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
						requestParameters(
								parameterWithName("questionId")
				                .description("question updation based on question id").attributes(key("required").value(true)),
				                parameterWithName("status")
				                .description("status true/false equals active/inactive question").attributes(key("required").value(true))
								)));
						
						
					
						
	}
	 

	@Test

	public void testAddQuestions() throws Exception {

		QuestionDTO questionDTO = new QuestionDTO();

		Answer answer = new Answer();
		answer.setOption("CSharp");
		answer.setRightAnswerExplanation("Csharp is ool");
		answer.setGrading(4);
		answer.setId(2);
		answer.setIsStricky(true);
		answer.setQuestionId(4);
		answer.setIsRightAnswer(true);

		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);

		questionDTO.setAnswer(answerList);
		questionDTO.setCategoryId(2);
		questionDTO.setTitle("cSharpbasics");
		questionDTO.setContent("What is Serializable");
		questionDTO.setDuration("1:1:30");
		questionDTO.setIsImported(true);
		questionDTO.setLevelId(3);
		questionDTO.setQuestionType(QuestionType.BestChoice);
		questionDTO.setScore(2);
		questionDTO.setSkillPoints(1);
		questionDTO.setTag("C sharp");
		questionDTO.setStatus(true);

		String addQuestion = objectMapper.writeValueAsString(questionDTO);
		when(questionService.saveQuestionAnswer(questionDTO)).thenReturn(true);
		this.mockMvc.perform(post("/question").contentType(MediaType.APPLICATION_JSON).content(addQuestion))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andDo(document("question/createquestion", preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestFields(
								fieldWithPath("title").description("Title of question")
										.attributes(key("required").value(true)),
								fieldWithPath("categoryId").description("Id of category")
										.attributes(key("required").value(true)),
								fieldWithPath("content").description("Question")
										.attributes(key("required").value(true)),
								fieldWithPath("duration").description("Time calculation based on question")
										.attributes(key("required").value(true)),
								fieldWithPath("isImported").description("whether the question is imported or not")
										.attributes(key("required").value(true)),
								fieldWithPath("levelId").description("Id of level")
										.attributes(key("required").value(true)),
								fieldWithPath("questionType").description("Type of question")
										.attributes(key("required").value(true)),
								fieldWithPath("score").description("Score for that question")
										.attributes(key("required").value(true)),
								fieldWithPath("skillPoints").description("Skill points for that question")
										.attributes(key("required").value(true)),
								fieldWithPath("tag").description("Question tag")
										.attributes(key("required").value(true)),
								fieldWithPath("answer[].option").description("Answer choices")
										.attributes(key("required").value(true)),
								fieldWithPath("answer[].rightAnswerExplanation").description("Right answer explanation")
										.attributes(key("required").value(true)),
								fieldWithPath("answer[].isStricky").description("Whether the answer is sticky or not")
										.attributes(key("required").value(true)),
								fieldWithPath("answer[].isRightAnswer")
										.description("Whether the answer is right or not")
										.attributes(key("required").value(true)),
								fieldWithPath("answer[].id").description("Answer Id")
										.attributes(key("required").value(true)),
								fieldWithPath("answer[].questionId").description("Question Id")
										.attributes(key("required").value(false)),
								fieldWithPath("answer[].grading").description("Answer grading")
										.attributes(key("required").value(true)),

								fieldWithPath("status").description("Whether the question is active or inactive")
										.attributes(key("not required").value(true)))));
	}

}
