package com.project.customer.base.enumaration;

public class CreditScoreEnum {

  public enum CreditScoreStatus {
    ACCEPT,
    REJECT
  }

  public enum CreditScoreLimit {
    CREDIT_SCORE_MIN_LIMIT(500L),
    CREDIT_SCORE_MAX_LIMIT(1000L);

    private Long limit;

    CreditScoreLimit(Long limit) {
      this.limit = limit;
    }

    public Long getLimit() {
      return limit;
    }
  }

  public enum CreditMultiplier {
    CREDIT_LIMIT_MULTIPLIER(4);

    private Integer creditLimitMultiplier;

    CreditMultiplier(Integer creditLimitMultiplier) {
      this.creditLimitMultiplier = creditLimitMultiplier;
    }

    public Integer getCreditLimitMultiplier() {
      return creditLimitMultiplier;
    }
  }
}
