package com.company.model;

import com.company.dto.MerchantStatus;
import com.company.dto.UserRole;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Merchant entity. */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "merchants")
public class Merchant implements Serializable {

  @Id
  @Column(name = "ID")
  @EqualsAndHashCode.Include
  private UUID id;

  @Setter
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Setter
  @Column(name = "description")
  private String description;

  @Setter
  @Column(name = "email", unique = true)
  private String email;

  @NotNull
  @Setter
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private MerchantStatus status;

  @Setter
  @Positive
  @Column(name = "total_transaction_sum")
  private Integer totalTransactionSum;

  @Setter
  @ToString.Exclude
  @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, orphanRemoval = true)
  @Builder.Default
  private List<Transaction> transactions = new ArrayList<>();

  @Setter
  @ElementCollection(targetClass = UserRole.class)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "merchant_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "role_name")
  private Set<UserRole> roles;
}
