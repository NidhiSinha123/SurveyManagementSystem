package com.sms.SurveyManagementSystem.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sms.SurveyManagementSystem.dto.AssignSurvey;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;
import com.sms.SurveyManagementSystem.service.SurveyManagementService;

@RestController
public class SurveyManagementController {

	@Autowired
	SurveyManagementService service;
	
@PostMapping(value="/addsurvey")
public ResponseEntity<?> addSurvey(@RequestBody Survey survey)
{
	Survey newSurvey=new Survey();
	try
	{
		Set<Questions> listOfQuestions=new HashSet<Questions>();
		List<User> userList=new ArrayList<User>();
		newSurvey.setSurveyName(survey.getSurveyName());
		newSurvey.setSurveyDescription(survey.getSurveyDescription());
		newSurvey.setSurveyType(survey.getSurveyType());
		newSurvey.setListOfQuestions(listOfQuestions);
		newSurvey.setUserList(userList);
		newSurvey.setDeleted(false);
		service.createSurvey(newSurvey);
	}
	catch(UserException exception)
	{
		return new ResponseEntity<Survey>(newSurvey, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	return new ResponseEntity<Survey>(newSurvey,HttpStatus.OK);
	
}

@DeleteMapping(value="/deleteSurvey")
public ResponseEntity<?> deleteSurvey(@RequestParam("surveyId")String id) throws UserException
{
	BigInteger surveyId=service.validateSurveyId(id,service.getSurveyList());
	if(surveyId!=null)
	{
		service.removeSurvey(surveyId);
		return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@PutMapping(value="/updateSurvey")
public ResponseEntity<?> updateSurvey(@RequestBody Survey survey) throws UserException
{
	Survey result=service.updateSurvey(survey.getSurveyId(), survey);
	if(result!=null)
	{
		return new ResponseEntity<Survey>(result,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("not updated",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@PostMapping(value="/register")
public ResponseEntity<?> registerUser(@RequestBody User user)
{
	User newUser=new User();
	try
	{
		newUser.setUserName(user.getUserName());
		newUser.setDeleted(false);
		newUser.setUserAge(user.getUserAge());
		newUser.setUserContactNo(user.getUserContactNo());
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUserGender(user.getUserGender());
		newUser.setUserRole("Role_Respondent");
		service.register(newUser);
	}
	catch(UserException exception)
	{
		return new ResponseEntity<User>(newUser, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	return new ResponseEntity<User>(newUser,HttpStatus.OK);
	
}
	
	  @PostMapping(value="/assignSurvey") 
	  public ResponseEntity<?>assignSurvey(@RequestBody AssignSurvey data) throws UserException { 
		try {
	 
	  boolean status=service.distributeSurvey(data.getUserId(), data.getSurveyId()); 
	  if(status==true)
	  return new ResponseEntity<String>("Survey assigned successfully",HttpStatus.OK);
	  else
		  return new ResponseEntity<String>("Survey not assigned successfully",HttpStatus.OK);  
	  }
		
	  catch(UserException exception) { 
		  return new ResponseEntity<String>("Survey not assigned",HttpStatus.INTERNAL_SERVER_ERROR);
	  } 
	  }
	  
	  
	
}