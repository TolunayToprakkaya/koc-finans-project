package com.project.customer.controller;

import com.project.customer.base.dto.CreditScoreRequest;
import com.project.customer.base.dto.Customer;
import com.project.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

  @Autowired
  private ICustomerService customerService;

  @PostMapping(value = "/inquireCreditScore", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> inquireCreditScore(@RequestBody CreditScoreRequest creditScoreRequest) {
    return ResponseEntity.ok(customerService.inquireCreditScore(creditScoreRequest));
  }
}
