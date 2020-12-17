package com.project.customer.base.dto;

public class Customer {

  private Long customerId;
  private Long identityNumber;
  private String name;
  private String lastname;
  private Long monthlyIncome;
  private Long phone;
  private Long creditScoreStatusId;
  private String creditScoreStatus;
  private Long limit;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getIdentityNumber() {
    return identityNumber;
  }

  public void setIdentityNumber(Long identityNumber) {
    this.identityNumber = identityNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Long getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(Long monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public Long getCreditScoreStatusId() {
    return creditScoreStatusId;
  }

  public void setCreditScoreStatusId(Long creditScoreStatusId) {
    this.creditScoreStatusId = creditScoreStatusId;
  }

  public String getCreditScoreStatus() {
    return creditScoreStatus;
  }

  public void setCreditScoreStatus(String creditScoreStatus) {
    this.creditScoreStatus = creditScoreStatus;
  }

  public Long getLimit() {
    return limit;
  }

  public void setLimit(Long limit) {
    this.limit = limit;
  }
}
