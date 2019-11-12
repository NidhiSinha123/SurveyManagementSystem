package com.sms.SurveyManagementSystem.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
/*
 * Author:  Nidhi
 * Description:Controller
 * Created on: November 11, 2019
 * 
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SurveyManagementController {

	@Autowired
	SurveyManagementService service;
	
	/*
	 * Author: 			Nidhi
	 * Description: 	Add survey 
	 * Created on: 		November 11, 2019 
	 * Input: 			Survey survey
	 * Output: 			survey
	 */ 	
@PostMapping(value="/addsurvey")
public ResponseEntity<?> addSurvey(@RequestBody Survey survey)
{
	Survey newSurvey=new Survey();
	try
	{
		List<Questions> listOfQuestions=new ArrayList<Questions>();
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
/*
 * Author: 			Nidhi
 * Description: 	Delete survey 
 * Created on: 		November 11, 2019 
 * Input: 			String
 * Output: 			String
 */ 	
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
/*
 * Author: 			Nidhi
 * Description: 	Update the details of survey 
 * Created on: 		November 11, 2019 
 * Input: 			Survey survey
 * Output: 			Survey
 */ 	

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
/*
 * Author: 			Nidhi
 * Description: 	Register user 
 * Created on: 		November 11, 2019 
 * Input: 			User user
 * Output: 			User
 */ 	
@PostMapping(value="/register")
public ResponseEntity<?> registerUser(@RequestBody User user)
{
	User newUser=new User();
	try
	{
		newUser.setUserName(user.getUserName());
		newUser.setDeleted(false);
		newUser.setUserPassword(user.getUserPassword());
		newUser.setUserAge(user.getUserAge());
		newUser.setUserContactNo(user.getUserContactNo());
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUserGender(user.getUserGender());
		newUser.setUserRole("Role_Respondent");
		newUser.setAssigned(false);
		newUser.setStatus("Pending");
		service.register(newUser);
	}
	catch(UserException exception)
	{
		return new ResponseEntity<User>(newUser, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	return new ResponseEntity<User>(newUser,HttpStatus.OK);
	
}
/*
 * Author: 			Nidhi
 * Description: 	Assign survey to the given user
 * Created on: 		November 11, 2019 
 * Input: 			BigInteger userId,BigInteger surveyId
 * Output: 			String
 */ 	
	
	  @PostMapping(value="/assignSurvey") 
	  public ResponseEntity<?>assignSurvey(@RequestParam BigInteger userId,@RequestParam BigInteger surveyId) throws UserException { 
		try {
	 
	 
	  boolean status=service.distributeSurvey(userId, surveyId); 
	  if(status==true)
	  return new ResponseEntity<String>("Survey assigned successfully",HttpStatus.OK);
	  else
		  return new ResponseEntity<String>("Survey not assigned successfully",HttpStatus.OK);  
	  }
		
	  catch(UserException exception) { 
		  return new ResponseEntity<String>("Survey not assigned",HttpStatus.INTERNAL_SERVER_ERROR);
	  } 
	  }
	  /*
		 * Author: 			Nidhi
		 * Description: 	Returns list of survey
		 * Created on: 		November 11, 2019 
		 * Input: 			
		 * Output: 			ResponseEntity
		 */ 	
	  @GetMapping(value="/getSurveyList")
	  public ResponseEntity<?> getListOfSurvey() throws UserException
	  {
		  List<Survey> listOfSurvey=service.getSurveyList();
		  if(listOfSurvey.size()!=0)
		  return new ResponseEntity<List>(listOfSurvey,HttpStatus.OK);
		  else
			  return new ResponseEntity<String>("list is not present",HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
	  /*
		 * Author: 			Nidhi
		 * Description: 	Add question in the given survey 
		 * Created on: 		November 11, 2019 
		 * Input: 			BigInteger surveyId,Question question
		 * Output: 			string
		 */ 	 
	  
	  @PostMapping(value="/addquestion")
	  public ResponseEntity<?> addQuestion(@RequestParam("surveyId")BigInteger surveyId,@RequestBody Questions question) throws UserException
	  {
		  Survey survey;
			try {
				
				survey = service.searchSurvey(surveyId);
				if(survey!= null) {
					Questions newQuestion=new Questions();
					newQuestion.setDeleted(false);
					newQuestion.setQuestionDescription(question.getQuestionDescription());
					newQuestion.setQuestionOptions(question.getQuestionOptions());
					newQuestion.setQuestionType(question.getQuestionType());
					newQuestion.setSurvey(survey);
					service.addQuestion(surveyId, newQuestion);
					
					return new ResponseEntity<String>("Question added Successfully", HttpStatus.OK);
				}
			} catch (UserException e) {
				
				return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<String>("Question could not be added", HttpStatus.INTERNAL_SERVER_ERROR); 

		  
	  }
	  /*
		 * Author: 			Nidhi
		 * Description: 	Deletes question from the given survey
		 * Created on: 		November 11, 2019 
		 * Input: 			BigInteger id
		 * Output: 			String
		 */ 	
	  @DeleteMapping(value="/deletequestion")
	  public ResponseEntity<?> deleteQuestion(@RequestParam("questionId") BigInteger id)
	  {

			try {
				Questions question = service.searchQuestion(id);
				service.deleteQuestion(question.getSurvey().getSurveyId(), question.getQuestionId());
				
			} catch (UserException e) {
				
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>("Question deleted successfully!", HttpStatus.OK);
	  }
	  /*
		 * Author: 			Nidhi
		 * Description: 	Returns list of questions of the given surveyId 
		 * Created on: 		November 11, 2019 
		 * Input: 			surveyId
		 * Output: 			List
		 */ 	
	  
	  @GetMapping(value="/getquestion")
	  public ResponseEntity<?> getQuestion(@RequestParam("surveyId") String surveyId) throws UserException
	  {
		 // List<Questions> listOfQuestion=service.getQuestionList(id);
		 // if(listOfQuestion.size()!=0)
		 // return new ResponseEntity<List>(listOfQuestion,HttpStatus.OK);
		 // else
		//	  return new ResponseEntity<String>("list is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		  BigInteger id=new BigInteger(surveyId);
		  List<Questions> listOfQuestion=service.getQuestionList(id);
			 if(listOfQuestion.size()!=0)
			 {
				 List<Questions> questions = new ArrayList<Questions>();
					listOfQuestion.forEach(question->{
						question.setSurvey(null);;
						if(!question.isDeleted())
							questions.add(question);
					});
					return new ResponseEntity<List<Questions>>(questions,HttpStatus.OK);
			 }
			 else
			 {
				 return new ResponseEntity<String>("list is not present",HttpStatus.INTERNAL_SERVER_ERROR);
			 }
	  }
	  /*
		 * Author: 			Nidhi
		 * Description: 	Returns list of users
		 * Created on: 		November 11, 2019 
		 * Output: 			List
		 */ 	
	  @GetMapping(value="/getUserList")
	  public ResponseEntity<?> getUserList() throws UserException
	  {
		  List<User> listOfUser=service.getUserList();
		 if(listOfUser.size()!=0)
		 {
			 List<User> users = new ArrayList<User>();
				listOfUser.forEach(user->{
					user.setSurvey(null);
					if(!user.isDeleted() && !user.isAssigned())
						users.add(user);
				});
				return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("list is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		  
	  }
	  /*
		 * Author: 			Nidhi
		 * Description: 	Lists all the user of the given assigned survey
		 * Created on: 		November 11, 2019 
		 * Input: 			String
		 * Output: 			List of users
		 */ 	
	  @GetMapping(value="/getUser")
	  public ResponseEntity<?> getUser(@RequestParam("surveyId")String sId) throws UserException
	  {
		  System.out.println(sId);
		 BigInteger surveyId=service.validateSurveyId(sId, service.getSurveyList());
		  List<User> listOfUser=service.getUser(surveyId);
		  
		 if(listOfUser.size()!=0)
		 {
			 List<User> users = new ArrayList<User>();
				listOfUser.forEach(user->{
					user.setSurvey(null);
					if(!user.isDeleted())
						users.add(user);
				});
				return new ResponseEntity<List<User>>(users,HttpStatus.OK);
			 }
		 else
		 {
			 return new ResponseEntity<String>("list is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		  
	  }
	  /*
		 * Author: 			Nidhi
		 * Description: 	Returns survey of the given user 
		 * Created on: 		November 11, 2019 
		 * Input: 			String
		 * Output: 			Survey
		 */ 	
	  @SuppressWarnings("unused")
	  @GetMapping(value="/getSurveyId")
	  public ResponseEntity<?> getSurvey(@RequestParam("userId") String uId) throws UserException
	  {
		  
		  BigInteger userId=new BigInteger(uId);
		  Survey survey=service.getSurveyByUserId(userId);
		  survey.setListOfQuestions(null);
		  survey.setUserList(null);
		 // String sid=surveyId.toString();
		  //System.out.println(sid);
		  if(survey!=null)
		  {
			  return new ResponseEntity<Survey>(survey,HttpStatus.OK);
		  }
		  else
		  {
			  return new ResponseEntity<String>("No user found",HttpStatus.INTERNAL_SERVER_ERROR);
		  }
			  
	  }
	  
	  @PostMapping(value="submitSurvey")
	  public ResponseEntity<?> submitSurvey(@RequestParam("userId") String uId) throws UserException
	  {
		  BigInteger userId=new BigInteger(uId);
		  boolean status=service.submitSurvey(userId);
		  if(status)
		  {
			  return new ResponseEntity<String>("submitted successfully",HttpStatus.OK);
		  }
		  else
		  {
			  return new ResponseEntity<String>("not submitted",HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	  }
	  
	  
	
}