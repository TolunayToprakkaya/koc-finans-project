package com.project.customer.customer;

import com.project.customer.base.dto.CreditScore;
import com.project.customer.base.dto.CreditScoreRequest;
import com.project.customer.base.dto.Customer;
import com.project.customer.base.enumaration.CreditScoreEnum;
import com.project.customer.base.mapper.DozerMapperUtility;
import com.project.customer.entity.CustomerEntity;
import com.project.customer.repository.CustomerRepository;
import com.project.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InquireCreditScoreTest {

  @Mock
  private RestTemplate restTemplate;
  @Mock
  private CustomerRepository customerRepository;
  @Mock
  private DozerMapperUtility dozerMapperUtility;
  @Spy
  @InjectMocks
  private CustomerServiceImpl customerService;


  private HttpHeaders headers = new HttpHeaders();
  HttpEntity entity = new HttpEntity(headers);
  Map<String, String> urlParams = new HashMap<>();
  URI uri;
  private HttpHeaders headersForSave = new HttpHeaders();
  private HttpEntity<CreditScore> requestEntity;

  private ResponseEntity<Integer> exchange;
  private CustomerEntity customerEntity;
  private CreditScoreRequest creditScoreRequest;
  private static Integer creditScoreNumber = 1500;
  private CreditScore creditScore;
  private static String mapId = "CustomerEntity_Customer";
  private Customer customer;


  @BeforeEach
  public void setUp() {
    initMocks(this);

    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    customerEntity = new CustomerEntity();
    customerEntity.setCustId(1L);
    customerEntity.setIdNmbr(1L);
    customerEntity.setName("Name");
    customerEntity.setLastname("Lastname");
    customerEntity.setMnthlyIncm(1000L);
    customerEntity.setPhone(123456789L);
    customerEntity.setCrrntCrdtScrStId(1L);
    customerEntity.setCrrntLimit(1000L);

    creditScoreRequest = new CreditScoreRequest();
    creditScoreRequest.setIdentityNumber(1L);
    creditScoreRequest.setName("Name");
    creditScoreRequest.setLastName("Lastname");
    creditScoreRequest.setMonthlyIncome(1000L);
    creditScoreRequest.setPhone(123456789L);

    urlParams.put("name", CreditScoreEnum.CreditScoreStatus.ACCEPT.name());
    uri = UriComponentsBuilder.fromUriString("http://CREDIT-SCORE-SERVICE/creditScoreStatus/fetchCreditScoreStatusIdByName/{name}").buildAndExpand(urlParams).toUri();

    creditScore = new CreditScore();
    creditScore.setCreditScoreId(1L);
    creditScore.setCustomerId(1L);
    creditScore.setCreditScoreStatusId(1L);

    headersForSave = new HttpHeaders();
    headersForSave.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headersForSave.setContentType(MediaType.APPLICATION_JSON);

    requestEntity = new HttpEntity<>(creditScore, headersForSave);

    customer = new Customer();
    customer.setCustomerId(1L);
    customer.setCreditScoreStatusId(1L);
    customer.setIdentityNumber(1L);
    customer.setLastname("Lastname");
    customer.setName("Name");
    customer.setMonthlyIncome(1000L);
    customer.setPhone(123456789L);
    customer.setLimit(1000L);

    exchange = new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @Test
  @DisplayName("Inquire Credit Score By Customer Service Test")
  @Disabled
  void consentTest() {
    Mockito.when(restTemplate.exchange(
        ArgumentMatchers.anyString(),
        ArgumentMatchers.any(HttpMethod.class),
        ArgumentMatchers.any(),
        ArgumentMatchers.<Class<Integer>>any()))
    .thenReturn(exchange);

    //Mockito.doReturn(exchange).when(restTemplate).exchange("http://CREDIT-SCORE-SERVICE/creditScore/calculateCreditScore", HttpMethod.GET, entity, Integer.class);
    Mockito.doReturn(customerEntity).when(customerRepository).findByIdNmbr(anyLong());
    Mockito.doReturn(exchange).when(restTemplate).exchange("http://CREDIT-SCORE-SERVICE/creditScoreStatus/fetchCreditScoreStatusIdByName/{name}", HttpMethod.GET, entity, Long.class);
    Mockito.doReturn(customerEntity).when(customerRepository).save(customerEntity);
    Mockito.doReturn(exchange).when(restTemplate).exchange("http://CREDIT-SCORE-SERVICE/creditScore/save", HttpMethod.POST, requestEntity, CreditScore.class);
    Mockito.when(dozerMapperUtility.map(customerEntity, Customer.class, mapId)).thenReturn(customer);

    Customer result = customerService.inquireCreditScore(creditScoreRequest);

    assertAll("Save Credit Score",
        () -> assertNotNull(result, "Response can not be null"),
        () -> assertEquals(creditScore.getCustomerId(), result.getCustomerId()));

    Mockito.verify(restTemplate, Mockito.times(1)).exchange("http://CREDIT-SCORE-SERVICE/creditScore/calculateCreditScore", HttpMethod.GET, entity, Integer.class);
    Mockito.verify(customerRepository, Mockito.times(1)).findByIdNmbr(anyLong());
    Mockito.verify(restTemplate, Mockito.times(1)).exchange("http://CREDIT-SCORE-SERVICE/creditScoreStatus/fetchCreditScoreStatusIdByName/{name}", HttpMethod.GET, entity, Long.class);
    Mockito.verify(customerRepository, Mockito.times(1)).save(customerEntity);
    Mockito.verify(restTemplate, Mockito.times(1)).exchange("http://CREDIT-SCORE-SERVICE/creditScore/save", HttpMethod.POST, requestEntity, CreditScore.class);
    Mockito.verify(dozerMapperUtility, Mockito.times(1)).map(customerEntity, Customer.class, mapId);
  }
}
