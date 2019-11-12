package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;

/*
 * Author:  Nidhi
 * Description:DTO
 * Created on: November 11, 2019
 * 
 */
public class AssignSurvey {

	private BigInteger surveyId;
	private BigInteger userId;
	public AssignSurvey()
	{
		
	}
	public AssignSurvey(BigInteger surveyId, BigInteger userId) {
		super();
		this.surveyId = surveyId;
		this.userId = userId;
	}
	public BigInteger getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(BigInteger surveyId) {
		this.surveyId = surveyId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((surveyId == null) ? 0 : surveyId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignSurvey other = (AssignSurvey) obj;
		if (surveyId == null) {
			if (other.surveyId != null)
				return false;
		} else if (!surveyId.equals(other.surveyId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AssignSurvey [surveyId=" + surveyId + ", userId=" + userId + "]";
	}
	
}
