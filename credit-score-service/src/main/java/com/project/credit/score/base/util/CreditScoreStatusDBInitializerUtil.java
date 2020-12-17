package com.project.credit.score.base.util;

import com.project.credit.score.entity.CreditScoreStatusEntity;
import com.project.credit.score.repository.CreditScoreStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CreditScoreStatusDBInitializerUtil {

  private final CreditScoreStatusRepository creditScoreStatusRepository;

  @PostConstruct
  private void initDb() {

    if (creditScoreStatusRepository.count() <= 0) {
      createCreditScoreStatuses();
    }
  }

  private void createCreditScoreStatuses() {
    CreditScoreStatusEntity creditScoreStatusEntity1 = CreditScoreStatusEntity.builder()
        .crdtScrStId(1L)
        .name("ACCEPT")
        .build();

    CreditScoreStatusEntity creditScoreStatusEntity2 = CreditScoreStatusEntity.builder()
        .crdtScrStId(2L)
        .name("REJECT")
        .build();

    creditScoreStatusRepository.saveAll(Arrays.asList(creditScoreStatusEntity1, creditScoreStatusEntity2));
  }

}
