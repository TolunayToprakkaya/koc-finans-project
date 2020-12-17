package com.project.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

  @GetMapping("/customerServiceFallBack")
  public String customerServiceFallBackMethod() {
    return "Customer Service is taking longer than excepted. " +
        "Please try again later";
  }

  @GetMapping("/creditScoreServiceFallBack")
  public String creditScoreServiceFallBackMethod() {
    return "Credit Score Service is taking longer than excepted. " +
        "Please try again later";
  }
}
