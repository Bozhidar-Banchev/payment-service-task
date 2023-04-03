package com.company.model;

import com.company.dto.TransactionStatus;
import com.company.dto.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

/** Transaction entity. */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

  @Id
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private UUID id;

  @Positive
  @Setter
  @Column(name = "amount")
  private Integer amount;

  @NotNull
  @Setter
  @Column(name = "status", nullable = false)
  private TransactionStatus status;

  @Setter
  @Column(name = "name")
  private String name;

  @Setter
  @ManyToOne
  @JoinColumn(name = "merchant_id")
  private Merchant merchant;

  @Setter
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @CreatedDate private LocalDateTime created;

  @Setter @Embedded private CustomerInfo customerInfo;
}
