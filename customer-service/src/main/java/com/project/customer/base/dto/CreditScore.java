package com.project.customer.base.dto;

public class CreditScore {

  private Long creditScoreId;
  private Long customerId;
  private Long creditScoreStatusId;

  public Long getCreditScoreId() {
    return creditScoreId;
  }

  public void setCreditScoreId(Long creditScoreId) {
    this.creditScoreId = creditScoreId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getCreditScoreStatusId() {
    return creditScoreStatusId;
  }

  public void setCreditScoreStatusId(Long creditScoreStatusId) {
    this.creditScoreStatusId = creditScoreStatusId;
  }
}
