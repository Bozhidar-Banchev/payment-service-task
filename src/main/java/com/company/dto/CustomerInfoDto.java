package com.company.dto;

import jakarta.validation.constraints.Email;
import java.io.Serializable;
import lombok.Data;

/** A DTO for the {@link com.company.model.CustomerInfo} entity */
@Data
public class CustomerInfoDto implements Serializable {

  @Email(message = "Please provide a valid email address")
  private final String email;

  private final String phone;
}
