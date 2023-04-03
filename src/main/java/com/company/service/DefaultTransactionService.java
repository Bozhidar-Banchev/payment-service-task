package com.company.service;

import com.company.dto.MerchantDto;
import com.company.dto.TransactionDto;
import com.company.exceprions.TransacitonNotFoundException;
import com.company.mapper.TransactionMapper;
import com.company.repository.TransactionRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/** Default implementation of {@link TransactionService} */
@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultTransactionService implements TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionMapper transactionMapper;

  @Override
  public List<TransactionDto> findAll() {

    return transactionRepository.findAll().stream()
        .map(transactionMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public TransactionDto findById(UUID id) {

    return transactionRepository
        .findById(id)
        .map(transactionMapper::entityToDto)
        .orElseThrow(
            () -> new TransacitonNotFoundException("Transaction dont exist with id: " + id));
  }
}
