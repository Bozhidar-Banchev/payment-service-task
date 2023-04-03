package com.company.mapper;

import com.company.dto.MerchantDto;
import com.company.model.Merchant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TransactionMapper.class)
public interface MerchantMapper {

  Merchant dtoToEntity(MerchantDto dto);

  MerchantDto entityToDto(Merchant entity);
}
