package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Set;
import lombok.Data;

/** A DTO for the {@link com.company.model.Merchant} entity */
@Data
public class MerchantDto {

  @NotBlank private final String name;

  private final String description;

  private final String email;

  @NotNull private final MerchantStatus status;

  @Positive private final Integer totalTransactionSum;

  private final List<TransactionDto> transactions;

  private final Set<UserRole> roles;
}
