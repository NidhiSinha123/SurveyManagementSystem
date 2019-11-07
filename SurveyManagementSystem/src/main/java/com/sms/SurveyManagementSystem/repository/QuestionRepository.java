package com.sms.SurveyManagementSystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.SurveyManagementSystem.dto.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,BigInteger> {

}
