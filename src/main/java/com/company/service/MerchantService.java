package com.company.service;

import com.company.dto.MerchantDto;
import java.util.List;
import java.util.UUID;

/** Service to manage merchants. */
interface MerchantService {

  List<MerchantDto> findAll();

  MerchantDto findByName(String name);

  MerchantDto create(MerchantDto dto);

  void delete(UUID merchantId);
}
