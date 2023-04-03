package com.company.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/** A DTO for the {@link com.company.model.Transaction} entity */
@Data
public class TransactionDto {

  @Positive private final Integer amount;

  @NotNull private final TransactionStatus status;

  private final String name;

  private final TransactionType type;

  private final CustomerInfoDto customerInfo;
}
