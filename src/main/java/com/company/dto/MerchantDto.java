package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** A DTO for the {@link com.company.model.Merchant} entity */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {

  @NotBlank private String name;

  private String description;

  private String email;

  @NotNull private MerchantStatus status;

  @Positive private Integer totalTransactionSum;

  private List<TransactionDto> transactions;

  private Set<UserRole> roles;
}
