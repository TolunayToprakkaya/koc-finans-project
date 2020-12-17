package com.project.credit.score.creditScore;

import com.project.credit.score.base.dto.CreditScore;
import com.project.credit.score.base.mapper.DozerMapperUtility;
import com.project.credit.score.entity.CreditScoreEntity;
import com.project.credit.score.repository.CreditScoreRepository;
import com.project.credit.score.service.impl.CreditScoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SaveCreditScoreTest {

  @Mock
  private DozerMapperUtility dozerMapperUtility;
  @Mock
  private CreditScoreRepository creditScoreRepository;
  @Spy
  @InjectMocks
  private CreditScoreServiceImpl creditScoreService;

  private CreditScore creditScore;
  private static String mapId = "CreditScoreEntity_CreditScore";
  private CreditScoreEntity creditScoreEntity;

  @BeforeEach
  public void setUp() {
    initMocks(this);

    creditScore = new CreditScore();
    creditScore.setCreditScoreId(1L);
    creditScore.setCustomerId(1L);
    creditScore.setCreditScoreStatusId(1L);

    creditScoreEntity = new CreditScoreEntity();
    creditScoreEntity.setCustId(1L);
    creditScoreEntity.setCrdtScrStId(1L);
    creditScoreEntity.setCrdtScrId(1L);
  }

  @Test
  @DisplayName("Save Credit Score Service Test")
  void consentTest() {
    Mockito.when(dozerMapperUtility.map(creditScore, CreditScoreEntity.class, mapId)).thenReturn(creditScoreEntity);
    Mockito.doReturn(creditScoreEntity).when(creditScoreRepository).save(creditScoreEntity);

    CreditScore result = creditScoreService.saveCreditScore(this.creditScore);

    assertAll("Save Credit Score",
        () -> assertNotNull(result, "Response can not be null"),
        () -> assertEquals(creditScore.getCreditScoreId(), result.getCreditScoreId()));

    Mockito.verify(dozerMapperUtility, Mockito.times(1)).map(creditScore, CreditScoreEntity.class, mapId);
    Mockito.verify(creditScoreRepository, Mockito.times(1)).save(creditScoreEntity);
  }
}
