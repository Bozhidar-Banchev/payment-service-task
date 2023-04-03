package com.company.repository;

import com.company.model.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

  List<Transaction> findByCreatedBefore(LocalDateTime minusHours);

}
