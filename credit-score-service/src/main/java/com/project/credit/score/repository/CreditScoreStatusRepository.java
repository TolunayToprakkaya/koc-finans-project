package com.project.credit.score.repository;

import com.project.credit.score.entity.CreditScoreStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditScoreStatusRepository extends JpaRepository<CreditScoreStatusEntity, Long> {

  CreditScoreStatusEntity findByName(String name);
}
