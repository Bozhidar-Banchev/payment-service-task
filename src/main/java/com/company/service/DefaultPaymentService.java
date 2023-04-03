package com.company.service;

import com.company.dto.MerchantDto;
import com.company.exceprions.MerchantNotFoundException;
import com.company.mapper.MerchantMapper;
import com.company.repository.MerchantRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/** Default implementation of {@link PaymentService} */
@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultPaymentService implements PaymentService {

  private final MerchantRepository merchantRepository;

  private final MerchantMapper merchantMapper;

  @Override
  public List<MerchantDto> findAll() {

    return merchantRepository.findAll().stream()
        .map(merchantMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public MerchantDto findByName(String name) {

    return merchantRepository
        .findByName(name)
        .map(merchantMapper::entityToDto)
        .orElseThrow(() -> new MerchantNotFoundException("No merchant with name: " + name));
  }
}
