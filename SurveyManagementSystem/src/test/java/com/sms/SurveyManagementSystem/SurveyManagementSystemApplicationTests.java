package com.sms.SurveyManagementSystem;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;
import com.sms.SurveyManagementSystem.repository.QuestionRepository;
import com.sms.SurveyManagementSystem.repository.SurveyRepository;
import com.sms.SurveyManagementSystem.repository.UserRepository;
import com.sms.SurveyManagementSystem.service.SurveyManagementService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyManagementSystemApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	SurveyManagementService userService;
	
	@Autowired
	SurveyRepository surveyrepository;
	
	@Autowired
	QuestionRepository questionrepository;
	
	@Autowired
	UserRepository userrepository;
	
	
	Survey survey1=new Survey();
	Questions question=new Questions();
	User user=new User();	
	
	@Test
	public void deleteSurvey()
	{
		Survey survey=restTemplate.getForObject("/deleteSurvey", Survey.class);
		assertThat(survey);
	}
	
	@Test
	public void addSurvey()
	{
		Survey survey=restTemplate.getForObject("/addsurvey", Survey.class);
		assertThat(survey);
	}
	
	@Test
	public void addQuestion()
	{
		Questions question=restTemplate.getForObject("/addquestion",Questions.class);
		assertThat(question);
	}
	
	@Test
	public void deleteQuestion()
	{
		Questions question=restTemplate.getForObject("/deletequestion",Questions.class);
		assertThat(question);
	}
	
	
	
	@Test
	public void testAddSurvey() throws UserException
	{
		survey1.setDeleted(false);
		survey1.setSurveyDescription("survey1");
		survey1.setSurveyName("survey1");
		survey1.setSurveyType("singlechoice");
		assertEquals(survey1, userService.createSurvey(survey1));
	}
	
	
}
