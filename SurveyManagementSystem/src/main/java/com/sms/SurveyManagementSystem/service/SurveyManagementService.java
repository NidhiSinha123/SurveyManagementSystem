package com.sms.SurveyManagementSystem.service;
import java.math.BigInteger;
import java.util.List;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;

public interface SurveyManagementService {

	public Survey createSurvey(Survey survey) throws UserException;
	public Survey updateSurvey(BigInteger surveyId,Survey survey) throws UserException;
	public boolean removeSurvey(BigInteger surveyId)throws UserException;
	public List<Survey> getSurveyList() throws UserException;
	public Survey searchSurveyById(BigInteger surveyId) throws UserException;
	public Questions addQuestion(BigInteger surveyId,Questions question) throws UserException;
	public boolean deleteQuestion(BigInteger surveyId,BigInteger questionId)throws UserException;
	public Questions updateQuestion(BigInteger surveyId,BigInteger questionId,Questions question)throws UserException;
	public boolean distributeSurvey(BigInteger userId,BigInteger surveyId)throws UserException;
	public List<User> viewNoOfRespondents(String surveyId);
	public List<User> viewPendingSurvey(String surveyId);
	public BigInteger validateSurveyId(String surveyId,List<Survey> surveyList) throws UserException;
	public User register(User user)throws UserException;
	public List<Survey> findAssignedSurvey(BigInteger userId) throws UserException;
	public BigInteger validateQuestionId(String questionId,List<Questions> questionList)throws UserException;
	public List<Questions> getQuestionList(BigInteger surveyId) throws UserException;
	public Survey searchSurvey(BigInteger surveyId)throws UserException;
	public Questions searchQuestion(BigInteger questionId)throws UserException;
	public List<User> getUserList() throws UserException;
	public List<User> getUser(BigInteger surveyId)throws UserException;
	
	
}
