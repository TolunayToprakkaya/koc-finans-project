package com.project.credit.score.controller;

import com.project.credit.score.base.enumaration.CreditScoreEnum;
import com.project.credit.score.service.ICreditScoreStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditScoreStatus")
public class CreditScoreStatusController {

  @Autowired
  private ICreditScoreStatusService creditScoreStatusService;

  @GetMapping(value = "/fetchCreditScoreStatusIdByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> fetchCreditScoreStatusIdByName(@PathVariable("name") String name) {
    return ResponseEntity.ok(creditScoreStatusService.fetchCreditScoreStatusIdByName(name));
  }
}
