package com.company.mapper;

import com.company.dto.TransactionDto;
import com.company.model.Transaction;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionMapper {

  Transaction dtoToEntity(TransactionDto dto);

  TransactionDto entityToDto(Transaction entity);
}
