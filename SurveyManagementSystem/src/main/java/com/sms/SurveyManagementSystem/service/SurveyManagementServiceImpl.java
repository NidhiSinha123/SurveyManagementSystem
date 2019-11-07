package com.sms.SurveyManagementSystem.service;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;
import com.sms.SurveyManagementSystem.repository.QuestionRepository;
import com.sms.SurveyManagementSystem.repository.SurveyRepository;
import com.sms.SurveyManagementSystem.repository.UserRepository;


@Service
@Transactional

public class SurveyManagementServiceImpl implements SurveyManagementService{

	
	@Autowired
	SurveyRepository surveyrepository;
	
	@Autowired
	QuestionRepository questionrepository;
	
	@Autowired
	UserRepository userrepository;
	

	
	  @Override 
	  public boolean distributeSurvey(BigInteger userId, BigInteger surveyId) throws UserException
	  {
		  // TODO Auto-generated method stub 
	  User user=userrepository.findById(userId).get();
	  Survey survey=surveyrepository.findById(surveyId).get(); 
	  if(user==null) {
		 throw new UserException("Userid not found"); }
	  if(user.getUserRole().equals("ROLE_ADMIN") && user.getUserRole().equals("ROLE_SURVEYOR")) 
	  { 
		  throw new UserException("Survey cannot be assigned to the admin or surveyor"); 
	  }
	  if(survey==null) { 
		  throw new UserException("SurveyId not present");
		
	  }
	  List<User> listOfUser=survey.getUserList();
	  listOfUser.add(user);
	  survey.setUserList(listOfUser);
	  user.setSurvey(survey);
	  surveyrepository.save(survey);
	  userrepository.save(user);
	  
	  return true;
	  }
	 	

	@Override
	public List<User> viewNoOfRespondents(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> viewPendingSurvey(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Survey createSurvey(Survey survey) throws UserException  {
		// TODO Auto-generated method stub
		Survey newSurvey=surveyrepository.save(survey);
		if(newSurvey == null)
		{
			throw new UserException("Survey is not added");
		}
		return newSurvey;
	}


	@Override
	public Survey updateSurvey(BigInteger surveyId,Survey survey) throws UserException {
		// TODO Auto-generated method stub
		
		Survey newSurvey=surveyrepository.findById(surveyId).get();
		if(newSurvey!=null)
		{
			newSurvey.setSurveyId(surveyId);
			newSurvey.setSurveyName(survey.getSurveyName());
			newSurvey.setSurveyDescription(survey.getSurveyDescription());
			newSurvey.setSurveyType(survey.getSurveyType());
			return newSurvey;
		}
		else
		{
			throw new UserException("Survey not updated");
		}
		
		
	}
	

	@Override
	public List<Survey> getSurveyList() throws UserException {
		// TODO Auto-generated method stub
		return surveyrepository.findAll();
	}
	
	public List<Questions> getQuestionList() throws UserException
	{
		return questionrepository.findAll();
	}

	@Override
	public BigInteger validateSurveyId(String surveyId, List<Survey> surveyList) throws UserException {
		// TODO Auto-generated method stub
		if (surveyId.matches("^[0-9]+")) {
			Iterator<Survey> surveyIterator = surveyList.iterator();
			while (surveyIterator.hasNext()) {
				Survey survey = surveyIterator.next();
				if (survey.getSurveyId().compareTo(new BigInteger(surveyId)) == 0) {
					
					return survey.getSurveyId();
				}
			}
		}
		
		throw new UserException("SurveyId is not present");
	
	}
	
	public BigInteger validateQuestionId(String questionId,List<Questions> questionList)throws UserException
	{
		if(questionId.matches("^[0-9]+"))
		{
			Iterator<Questions> questionIterator=questionList.iterator();
			while(questionIterator.hasNext())
			{
				Questions question=questionIterator.next();
				if(question.getQuestionId().compareTo(new BigInteger(questionId))==0)
				{
					return question.getQuestionId();
				}
			}
		}
		throw new UserException("QuestionId not found");
	}

	@Override
	public boolean removeSurvey(BigInteger surveyId) throws UserException {
		// TODO Auto-generated method stub
		Survey newSurvey=surveyrepository.findById(surveyId).get();
		if(newSurvey==null)
		{
			throw new UserException("Survey Id doesn't exist");
		}
		newSurvey.setDeleted(true);
		return true;
		
	}

	@Override
	public Survey searchSurveyById(BigInteger surveyId) throws UserException {
		// TODO Auto-generated method stub
		Survey survey=surveyrepository.findById(surveyId).get();
		if(survey==null)
		{
			throw new UserException("Survey with surveyId "+surveyId+" not found");
		}
		return survey;
	}

	@Override
	public Questions addQuestion(BigInteger surveyId, Questions question) throws UserException {
		// TODO Auto-generated method stub
		Survey survey=surveyrepository.findById(surveyId).get();
		if(survey!=null)
		{
			
			//System.out.println(question);
			
			//question.setSurvey(survey);
			Set<Questions> questionList=survey.getListOfQuestions();
			
			
			System.out.println(survey.getListOfQuestions());
			Questions newquestion=new Questions();
			newquestion.setAnswer(question.getAnswer());
			newquestion.setChosenOptions(question.getChosenOptions());
			newquestion.setDeleted(false);
			newquestion.setQuestionDescription(question.getQuestionDescription());
			newquestion.setQuestionNumber(question.getQuestionNumber());
			newquestion.setQuestionOptions(question.getQuestionOptions());
			newquestion.setQuestionTitle(question.getQuestionTitle());
			newquestion.setQuestionType(question.getQuestionType());
			newquestion.setSurvey(survey);
			questionrepository.save(newquestion);
			questionList.add(newquestion);
			survey.setListOfQuestions(questionList);
			surveyrepository.save(survey);
		}
		else
		{
			throw new UserException("Survey not found");
		}
		return question;
	}

	@Override
	public boolean deleteQuestion(BigInteger surveyId, BigInteger questionId) throws UserException {
		// TODO Auto-generated method stub
		Survey survey=surveyrepository.findById(surveyId).get();
		if(survey == null)
		{
			throw new UserException("Survey not present");
		}
		Questions question=questionrepository.findById(questionId).get();
		if(question==null)
		{
			throw new UserException("Question not found");
		}
		question.setDeleted(true);
		//survey.getListOfQuestions().remove(question);
		questionrepository.save(question);
		//surveyrepository.save(survey);
		return true;
		
	}

	@Override
	public Questions updateQuestion(BigInteger surveyId, BigInteger questionId, Questions question)
			throws UserException {
		// TODO Auto-generated method stub
		Survey survey=surveyrepository.findById(surveyId).get();
		if(survey==null)
			throw new UserException("Survey not found");
		Set<Questions> listOfQuestions=survey.getListOfQuestions();
		Questions temp=questionrepository.findById(questionId).get();
		if(temp!=null && listOfQuestions.contains(temp))
		{
			listOfQuestions.remove(temp);
			temp.setAnswer(question.getAnswer());
			temp.setChosenOptions(question.getChosenOptions());
			temp.setDeleted(question.isDeleted());
			temp.setQuestionDescription(question.getQuestionDescription());
			temp.setQuestionId(question.getQuestionId());
			temp.setQuestionNumber(question.getQuestionNumber());
			temp.setQuestionOptions(question.getQuestionOptions());
			temp.setQuestionTitle(question.getQuestionTitle());
			temp.setQuestionType(question.getQuestionType());
			temp.setSurvey(question.getSurvey());
			listOfQuestions.add(temp);
			survey.setListOfQuestions(listOfQuestions);
			questionrepository.save(temp);
			surveyrepository.save(survey);
			
		}
		return question;
	}

	@Override
	public Questions searchQuestion(BigInteger questionId) throws UserException {
		// TODO Auto-generated method stub
		Questions question=questionrepository.findById(questionId).get();
		if(question==null)
		{
			throw new UserException("question with questionId"+questionId+"not present");
		}
		return question;
		
	}



	@Override
	public User register(User user) throws UserException {
		// TODO Auto-generated method stub
		User newUser=userrepository.save(user);
		if(newUser==null)
		{
			 throw new UserException("User not registered");
		}
		return newUser;
	}



	@Override
	public List<Survey> findAssignedSurvey(BigInteger userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}





	

}
