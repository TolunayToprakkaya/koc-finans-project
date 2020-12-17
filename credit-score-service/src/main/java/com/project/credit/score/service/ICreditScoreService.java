package com.project.credit.score.service;

import com.project.credit.score.base.dto.CreditScore;

import java.util.List;

public interface ICreditScoreService {

  Integer calculateCreditScore();

  List<CreditScore> inquireCreditScoreByCustomer(Long customerId);

  CreditScore saveCreditScore(CreditScore creditScore);
}
