package com.project.customer.base.dto;

public class CreditScoreRequest {

  private Long identityNumber;
  private String name;
  private String lastName;
  private Long monthlyIncome;
  private Long phone;

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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
}
