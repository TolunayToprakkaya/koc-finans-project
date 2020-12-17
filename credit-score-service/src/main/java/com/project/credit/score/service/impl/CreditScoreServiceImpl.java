package com.project.credit.score.service.impl;

import com.project.credit.score.base.dto.CreditScore;
import com.project.credit.score.base.mapper.DozerMapperUtility;
import com.project.credit.score.entity.CreditScoreEntity;
import com.project.credit.score.repository.CreditScoreRepository;
import com.project.credit.score.service.ICreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CreditScoreServiceImpl implements ICreditScoreService {

  @Autowired
  private CreditScoreRepository creditScoreRepository;

  @Autowired
  private DozerMapperUtility dozerMapperUtility;

  @Override
  public Integer calculateCreditScore() {
    Random random = new Random();

    return random.nextInt(1500);
  }

  @Override
  public List<CreditScore> inquireCreditScoreByCustomer(Long customerId) {
    List<CreditScoreEntity> creditScoreEntityList = creditScoreRepository.findByCustId(customerId);

    return dozerMapperUtility.map(creditScoreEntityList, CreditScore.class, "CreditScoreEntity_CreditScore");
  }

  @Override
  public CreditScore saveCreditScore(CreditScore creditScore) {
    CreditScoreEntity creditScoreEntity = dozerMapperUtility.map(creditScore, CreditScoreEntity.class, "CreditScoreEntity_CreditScore");

    creditScoreRepository.save(creditScoreEntity);
    return creditScore;
  }
}
