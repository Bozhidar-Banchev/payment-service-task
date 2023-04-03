package com.company.service;

import com.company.dto.TransactionDto;
import java.util.List;
import java.util.UUID;

/** Service to manage transactions. */
interface TransactionService {

  List<TransactionDto> findAll();

  TransactionDto findById(UUID id);
}
