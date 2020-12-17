package com.project.customer.service;

import com.project.customer.base.dto.CreditScoreRequest;
import com.project.customer.base.dto.Customer;

public interface ICustomerService {

  Customer inquireCreditScore(CreditScoreRequest creditScoreRequest);
}
