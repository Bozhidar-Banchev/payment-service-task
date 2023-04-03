package com.company.schedule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.model.Transaction;
import com.company.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionDeleterTest {

  @Mock private TransactionRepository transactionRepository;

  @InjectMocks private TransactionsJob transactionDeleter;

  @Test
  void shouldLogNumberOfDeletedTransactions() {
    // GIVEN
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(Transaction.builder().name("T1").build());
    transactions.add(Transaction.builder().name("T2").build());

    when(transactionRepository.findByCreatedBefore(any())).thenReturn(transactions);

    // WHEN
    transactionDeleter.deleteTransaction();

    // THEN
    verify(transactionRepository, times(1)).deleteAll(transactions);
  }
}
