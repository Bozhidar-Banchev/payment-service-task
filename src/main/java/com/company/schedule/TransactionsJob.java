package com.company.schedule;

import com.company.model.Transaction;
import com.company.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
@EnableScheduling
public class TransactionsJob {

  private final TransactionRepository transactionRepository;

  @Scheduled(cron = "${transaction.scheduler.cron-expression}")
  @Transactional
  public void deleteTransaction() {
    List<Transaction> transactions =
        transactionRepository.findByCreatedBefore(LocalDateTime.now().minusHours(1));

    if (CollectionUtils.isNotEmpty(transactions)) {
      transactionRepository.deleteAll(transactions);
      log.info("Transactions deleted: " + transactions.size());
    }
  }
}
