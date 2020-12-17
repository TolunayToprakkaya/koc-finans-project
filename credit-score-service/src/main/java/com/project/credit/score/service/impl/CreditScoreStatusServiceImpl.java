package com.project.credit.score.service.impl;

import com.project.credit.score.entity.CreditScoreStatusEntity;
import com.project.credit.score.repository.CreditScoreStatusRepository;
import com.project.credit.score.service.ICreditScoreStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreStatusServiceImpl implements ICreditScoreStatusService {

  @Autowired
  private CreditScoreStatusRepository creditScoreStatusRepository;

  @Override
  public Long fetchCreditScoreStatusIdByName(String name) {
    CreditScoreStatusEntity creditScoreStatusEntity = creditScoreStatusRepository.findByName(name);

    return creditScoreStatusEntity.getCrdtScrStId();
  }
}
