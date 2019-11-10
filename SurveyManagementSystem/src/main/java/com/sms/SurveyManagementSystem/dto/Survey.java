package com.sms.SurveyManagementSystem.dto;


import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="survey_id")
	private BigInteger surveyId;
	
	@Column(name="survey_name")
	private String surveyName;
	
	@Column(name="survey_description")
	private String surveyDescription;
	
	@Column(name="survey_type")
	private String surveyType;
	
	@OneToMany(mappedBy = "survey")
	private List<Questions> listOfQuestions;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@OneToMany(mappedBy="survey")
	private List<User> userList;
	
	
	public Survey()
	{
		
	}


	public Survey(BigInteger surveyId, String surveyName, String surveyDescription, String surveyType,
			List<Questions> listOfQuestions, boolean isDeleted, List<User> userList) {
		super();
		this.surveyId = surveyId;
		this.surveyName = surveyName;
		this.surveyDescription = surveyDescription;
		this.surveyType = surveyType;
		this.listOfQuestions = listOfQuestions;
		this.isDeleted = isDeleted;
		this.userList = userList;
	}


	public BigInteger getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(BigInteger surveyId) {
		this.surveyId = surveyId;
	}


	public String getSurveyName() {
		return surveyName;
	}


	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}


	public String getSurveyDescription() {
		return surveyDescription;
	}


	public void setSurveyDescription(String surveyDescription) {
		this.surveyDescription = surveyDescription;
	}


	public String getSurveyType() {
		return surveyType;
	}


	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}


	public List<Questions> getListOfQuestions() {
		return listOfQuestions;
	}


	public void setListOfQuestions(List<Questions> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public List<User> getUserList() {
		return userList;
	}


	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", surveyName=" + surveyName + ", surveyDescription="
				+ surveyDescription + ", surveyType=" + surveyType + ", listOfQuestions=" + listOfQuestions
				+ ", isDeleted=" + isDeleted + ", userList=" + userList + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + ((listOfQuestions == null) ? 0 : listOfQuestions.hashCode());
		result = prime * result + ((surveyDescription == null) ? 0 : surveyDescription.hashCode());
		result = prime * result + ((surveyId == null) ? 0 : surveyId.hashCode());
		result = prime * result + ((surveyName == null) ? 0 : surveyName.hashCode());
		result = prime * result + ((surveyType == null) ? 0 : surveyType.hashCode());
		result = prime * result + ((userList == null) ? 0 : userList.hashCode());
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
		Survey other = (Survey) obj;
		if (isDeleted != other.isDeleted)
			return false;
		if (listOfQuestions == null) {
			if (other.listOfQuestions != null)
				return false;
		} else if (!listOfQuestions.equals(other.listOfQuestions))
			return false;
		if (surveyDescription == null) {
			if (other.surveyDescription != null)
				return false;
		} else if (!surveyDescription.equals(other.surveyDescription))
			return false;
		if (surveyId == null) {
			if (other.surveyId != null)
				return false;
		} else if (!surveyId.equals(other.surveyId))
			return false;
		if (surveyName == null) {
			if (other.surveyName != null)
				return false;
		} else if (!surveyName.equals(other.surveyName))
			return false;
		if (surveyType == null) {
			if (other.surveyType != null)
				return false;
		} else if (!surveyType.equals(other.surveyType))
			return false;
		if (userList == null) {
			if (other.userList != null)
				return false;
		} else if (!userList.equals(other.userList))
			return false;
		return true;
	}

	

}