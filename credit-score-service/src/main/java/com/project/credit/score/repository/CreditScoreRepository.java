package com.project.credit.score.repository;

import com.project.credit.score.entity.CreditScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditScoreRepository extends JpaRepository<CreditScoreEntity, Long> {

  List<CreditScoreEntity> findByCustId(Long custId);
}
