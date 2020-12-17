package com.project.customer.service.impl;

import com.project.customer.base.dto.CreditScore;
import com.project.customer.base.dto.CreditScoreRequest;
import com.project.customer.base.dto.Customer;
import com.project.customer.base.enumaration.CreditScoreEnum.*;
import com.project.customer.base.mapper.DozerMapperUtility;
import com.project.customer.entity.CustomerEntity;
import com.project.customer.repository.CustomerRepository;
import com.project.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements ICustomerService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private DozerMapperUtility dozerMapperUtility;

  @Override
  public Customer inquireCreditScore(CreditScoreRequest creditScoreRequest) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity entity = new HttpEntity(headers);

    ResponseEntity<Integer> exchange = restTemplate.exchange("http://CREDIT-SCORE-SERVICE/creditScore/calculateCreditScore", HttpMethod.GET, entity, Integer.class);
    Integer creditScore = exchange.getBody();

    CustomerEntity customerEntity = customerRepository.findByIdNmbr(creditScoreRequest.getIdentityNumber());
    if (customerEntity == null) {
      customerEntity = this.fillCustomerEntity(creditScoreRequest);

      return this.calculateLimit(customerEntity, creditScoreRequest, creditScore);
    } else {
      return this.calculateLimit(customerEntity, creditScoreRequest, creditScore);
    }
  }

  private CustomerEntity fillCustomerEntity(CreditScoreRequest creditScoreRequest) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setIdNmbr(creditScoreRequest.getIdentityNumber());
    customerEntity.setName(creditScoreRequest.getName());
    customerEntity.setLastname(creditScoreRequest.getLastName());
    customerEntity.setMnthlyIncm(creditScoreRequest.getMonthlyIncome());
    customerEntity.setPhone(creditScoreRequest.getPhone());

    return customerEntity;
  }

  private Customer calculateLimit(CustomerEntity customerEntity, CreditScoreRequest creditScoreRequest, Integer creditScore) {
    if (creditScore < CreditScoreLimit.CREDIT_SCORE_MIN_LIMIT.getLimit()) {
      Long creditScoreStatusId = this.creditScoreStatusOpenApiGateWay(CreditScoreStatus.REJECT.name());
      customerEntity.setCrrntCrdtScrStId(creditScoreStatusId);
      customerEntity.setCrrntLimit(0L);

      customerEntity = customerRepository.save(customerEntity);
      //Save data "crd_scr" table
      this.saveCreditScore(customerEntity, creditScoreStatusId);

      Customer customer = dozerMapperUtility.map(customerEntity, Customer.class, "CustomerEntity_Customer");
      customer.setCreditScoreStatus(CreditScoreStatus.REJECT.toString());

      return customer;
    } else if (creditScore > CreditScoreLimit.CREDIT_SCORE_MIN_LIMIT.getLimit() && creditScore < CreditScoreLimit.CREDIT_SCORE_MAX_LIMIT.getLimit()) {
      Long creditScoreStatusId = this.creditScoreStatusOpenApiGateWay(CreditScoreStatus.ACCEPT.name());
      customerEntity.setCrrntCrdtScrStId(creditScoreStatusId);

      if (customerEntity.getMnthlyIncm() < 5000) {
        customerEntity.setCrrntLimit(10000L);
      } else {
        long limit = creditScoreRequest.getMonthlyIncome() * CreditMultiplier.CREDIT_LIMIT_MULTIPLIER.getCreditLimitMultiplier();
        customerEntity.setCrrntLimit(limit);
      }

      customerEntity = customerRepository.save(customerEntity);
      //Save data "crd_scr" table
      this.saveCreditScore(customerEntity, creditScoreStatusId);

      Customer customer = dozerMapperUtility.map(customerEntity, Customer.class, "CustomerEntity_Customer");
      customer.setCreditScoreStatus(CreditScoreStatus.ACCEPT.toString());

      return customer;
    } else if (creditScore >= CreditScoreLimit.CREDIT_SCORE_MAX_LIMIT.getLimit()) {
      Long creditScoreStatusId = this.creditScoreStatusOpenApiGateWay(CreditScoreStatus.ACCEPT.name());
      customerEntity.setCrrntCrdtScrStId(creditScoreStatusId);

      long limit = creditScoreRequest.getMonthlyIncome() * CreditMultiplier.CREDIT_LIMIT_MULTIPLIER.getCreditLimitMultiplier();
      customerEntity.setCrrntLimit(limit);

      customerEntity = customerRepository.save(customerEntity);
      //Save data "crd_scr" table
      this.saveCreditScore(customerEntity, creditScoreStatusId);

      Customer customer = dozerMapperUtility.map(customerEntity, Customer.class, "CustomerEntity_Customer");
      customer.setCreditScoreStatus(CreditScoreStatus.ACCEPT.toString());

      return customer;
    }

    return null;
  }

  private Long creditScoreStatusOpenApiGateWay(String name) {
    final String fetchCreditScoreStatusIdByNameUrl = "http://CREDIT-SCORE-SERVICE/creditScoreStatus/fetchCreditScoreStatusIdByName/{name}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity entity = new HttpEntity(headers);
    Map<String, String> urlParams = new HashMap<>();
    urlParams.put("name", name);

    URI uri = UriComponentsBuilder.fromUriString(fetchCreditScoreStatusIdByNameUrl).buildAndExpand(urlParams).toUri();

    ResponseEntity<Long> creditScoreStatus = restTemplate.exchange(uri, HttpMethod.GET, entity, Long.class);

    return creditScoreStatus.getBody();
  }

  private void saveCreditScore(CustomerEntity customerEntity, Long creditScoreStatusId) {
    CreditScore creditScoreDTO = new CreditScore();
    creditScoreDTO.setCustomerId(customerEntity.getCustId());
    creditScoreDTO.setCreditScoreStatusId(creditScoreStatusId);

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreditScore> requestEntity = new HttpEntity<>(creditScoreDTO, headers);
    restTemplate.exchange("http://CREDIT-SCORE-SERVICE/creditScore/save", HttpMethod.POST, requestEntity, CreditScore.class);
  }
}
