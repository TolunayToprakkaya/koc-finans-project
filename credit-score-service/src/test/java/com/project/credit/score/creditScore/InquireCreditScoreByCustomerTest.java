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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InquireCreditScoreByCustomerTest {

  @Mock
  private CreditScoreRepository creditScoreRepository;
  @Mock
  private DozerMapperUtility dozerMapperUtility;
  @Spy
  @InjectMocks
  private CreditScoreServiceImpl creditScoreService;

  private CreditScoreEntity creditScoreEntity;
  private List<CreditScoreEntity> creditScoreEntityList = new ArrayList<>();
  private static String mapId = "CreditScoreEntity_CreditScore";
  private CreditScore creditScore;
  private List<CreditScore> creditScoreList = new ArrayList<>();

  @BeforeEach
  public void setUp() {
    initMocks(this);

    creditScoreEntity = new CreditScoreEntity();
    creditScoreEntity.setCrdtScrId(1L);
    creditScoreEntity.setCustId(1L);
    creditScoreEntity.setCrdtScrStId(1L);
    creditScoreEntityList.add(creditScoreEntity);

    creditScore = new CreditScore();
    creditScore.setCreditScoreId(1L);
    creditScore.setCustomerId(1L);
    creditScore.setCreditScoreStatusId(1L);
    creditScoreList.add(creditScore);
  }

  @Test
  @DisplayName("Inquire Credit Score By Customer Service Test")
  void consentTest() {
    Mockito.doReturn(creditScoreEntityList).when(creditScoreRepository).findByCustId(anyLong());
    Mockito.when(dozerMapperUtility.map(creditScoreEntityList, CreditScore.class, mapId)).thenReturn(creditScoreList);

    List<CreditScore> result = creditScoreService.inquireCreditScoreByCustomer(anyLong());

    assertAll("Inquire Credit Score By Customer",
        () -> assertNotNull(result, "Response can not be null"),
        () -> assertEquals(creditScore.getCreditScoreId(), result.get(0).getCreditScoreId()));

    Mockito.verify(creditScoreRepository, Mockito.times(1)).findByCustId(anyLong());
    Mockito.verify(dozerMapperUtility, Mockito.times(1)).map(creditScoreEntityList, CreditScore.class, mapId);
  }

}
