package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="question_id")
	private BigInteger questionId;
	
	@Column(name="question_type")
	private String questionType;
	
	@Column(name="question_title")
	private String questionTitle;
	
	@Column(name="question_number")
	private Integer questionNumber;
	
	@Column(name="question_description")
	private String questionDescription;
	
	@Column(name="question_options")
	private String[] questionOptions;
	
	@Column(name="question_chosen_options")
	private Integer[] chosenOptions;
	
	@Column(name="question_answer")
	private String answer;
	
 	@ManyToOne
	@JoinColumn(name="survey_id")
	private Survey survey;
	
 	@Column(name="question_isDeleted")
 	private boolean isDeleted;
	
	public Questions()
	{
		
	}

	public Questions(BigInteger questionId, String questionType, String questionTitle, Integer questionNumber,
			String questionDescription, String[] questionOptions, Integer[] chosenOptions, String answer, Survey survey,
			boolean isDeleted) {
		super();
		this.questionId = questionId;
		this.questionType = questionType;
		this.questionTitle = questionTitle;
		this.questionNumber = questionNumber;
		this.questionDescription = questionDescription;
		this.questionOptions = questionOptions;
		this.chosenOptions = chosenOptions;
		this.answer = answer;
		this.survey = survey;
		this.isDeleted = isDeleted;
	}

	public BigInteger getQuestionId() {
		return questionId;
	}

	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String[] getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(String[] questionOptions) {
		this.questionOptions = questionOptions;
	}

	public Integer[] getChosenOptions() {
		return chosenOptions;
	}

	public void setChosenOptions(Integer[] chosenOptions) {
		this.chosenOptions = chosenOptions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + Arrays.hashCode(chosenOptions);
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + ((questionDescription == null) ? 0 : questionDescription.hashCode());
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((questionNumber == null) ? 0 : questionNumber.hashCode());
		result = prime * result + Arrays.hashCode(questionOptions);
		result = prime * result + ((questionTitle == null) ? 0 : questionTitle.hashCode());
		result = prime * result + ((questionType == null) ? 0 : questionType.hashCode());
		result = prime * result + ((survey == null) ? 0 : survey.hashCode());
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
		Questions other = (Questions) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (!Arrays.equals(chosenOptions, other.chosenOptions))
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (questionDescription == null) {
			if (other.questionDescription != null)
				return false;
		} else if (!questionDescription.equals(other.questionDescription))
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (questionNumber == null) {
			if (other.questionNumber != null)
				return false;
		} else if (!questionNumber.equals(other.questionNumber))
			return false;
		if (!Arrays.equals(questionOptions, other.questionOptions))
			return false;
		if (questionTitle == null) {
			if (other.questionTitle != null)
				return false;
		} else if (!questionTitle.equals(other.questionTitle))
			return false;
		if (questionType == null) {
			if (other.questionType != null)
				return false;
		} else if (!questionType.equals(other.questionType))
			return false;
		if (survey == null) {
			if (other.survey != null)
				return false;
		} else if (!survey.equals(other.survey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", questionType=" + questionType + ", questionTitle="
				+ questionTitle + ", questionNumber=" + questionNumber + ", questionDescription=" + questionDescription
				+ ", questionOptions=" + Arrays.toString(questionOptions) + ", chosenOptions="
				+ Arrays.toString(chosenOptions) + ", answer=" + answer + ", survey=" + survey + ", isDeleted="
				+ isDeleted + "]";
	}

	
	
}
