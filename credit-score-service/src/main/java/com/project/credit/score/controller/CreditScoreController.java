package com.project.credit.score.controller;

import com.project.credit.score.base.dto.CreditScore;
import com.project.credit.score.service.ICreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/creditScore")
public class CreditScoreController {

  @Autowired
  private ICreditScoreService creditScoreService;

  @GetMapping(value = "/calculateCreditScore", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> calculateCreditScore() {
    return ResponseEntity.ok(creditScoreService.calculateCreditScore());
  }

  @GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CreditScore>> inquireCreditScoreByCustomer(@PathVariable("custId") Long custId) {
    return ResponseEntity.ok(creditScoreService.inquireCreditScoreByCustomer(custId));
  }

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreditScore> saveCreditScore(@RequestBody CreditScore creditScore) {
    return ResponseEntity.ok(creditScoreService.saveCreditScore(creditScore));
  }
}
