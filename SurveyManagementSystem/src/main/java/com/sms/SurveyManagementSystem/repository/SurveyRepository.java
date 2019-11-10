package com.sms.SurveyManagementSystem.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.SurveyManagementSystem.dto.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,BigInteger> {
	@Query("FROM Survey WHERE isDeleted=false")
	public List<Survey> findAllNotDeleted();

}
