package com.company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Embeddable
public class CustomerInfo {

  @Email(message = "Please provide a valid email address")
  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;
}
