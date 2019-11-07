package com.sms.SurveyManagementSystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.SurveyManagementSystem.dto.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,BigInteger> {

}
