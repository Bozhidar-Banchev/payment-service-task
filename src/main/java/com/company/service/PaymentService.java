package com.company.service;

import com.company.dto.MerchantDto;
import java.util.List;

/** Service to manage payments. */
interface PaymentService {

  List<MerchantDto> findAll();

  MerchantDto findByName(String name);
}
