package com.company.mapper;

import com.company.dto.TransactionDto;
import com.company.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

  Transaction dtoToEntity(TransactionDto dto);

  TransactionDto entityToDto(Transaction entity);
}
