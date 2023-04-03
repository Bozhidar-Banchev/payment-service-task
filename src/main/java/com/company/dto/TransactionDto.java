package com.company.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** A DTO for the {@link com.company.model.Transaction} entity */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionDto {

  @Positive private Integer amount;

  @NotNull private TransactionStatus status;

  private String name;

  private TransactionType type;

  private CustomerInfoDto customerInfo;
}
