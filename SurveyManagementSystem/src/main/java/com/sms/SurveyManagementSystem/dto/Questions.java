package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/*
 * Author:  Nidhi
 * Description:DTO
 * Created on: November 11, 2019
 * 
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="question_id")
	private BigInteger questionId;
	
	@Column(name="question_description")
	private String questionDescription; 
	
	@Column(name="question_type")
	private String questionType;
	
	//@Column(name="question_title")
	//private String questionTitle;
	
	//@Column(name="question_number")
	//private Integer questionNumber;
	
	@Column(name="question_options")
	private String questionOptions;
	
	
	//@Column(name="question_chosen_options")
	//private Integer[] chosenOptions;
	
	//@Column(name="question_answer")
	//private String answer;
	
 	@ManyToOne
	@JoinColumn(name="survey_id")
	private Survey survey;
	
 	@Column(name="question_isDeleted")
 	private boolean isDeleted;
 	
 	@CreatedBy
	protected String createdBy;
	
	@CreatedDate	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	@LastModifiedDate
	protected String lastModifiedDate;
	
	public Questions()
	{
		
	}

	public Questions(BigInteger questionId, String questionDescription, String questionType, String questionOptions,
			Survey survey, boolean isDeleted) {
		super();
		this.questionId = questionId;
		this.questionDescription = questionDescription;
		this.questionType = questionType;
		this.questionOptions = questionOptions;
		this.survey = survey;
		this.isDeleted = isDeleted;
	}

	public BigInteger getQuestionId() {
		return questionId;
	}

	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(String questionOptions) {
		this.questionOptions = questionOptions;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	
}
