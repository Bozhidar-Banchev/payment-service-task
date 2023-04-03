package com.company.service;

import com.company.dto.MerchantDto;
import com.company.exceprions.MerchantNotFoundException;
import com.company.mapper.MerchantMapper;
import com.company.model.Merchant;
import com.company.repository.MerchantRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

/** Default implementation of {@link MerchantService} */
@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultMerchantService implements MerchantService {

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

  @Override
  public MerchantDto create(MerchantDto dto) {
    Merchant merchant = merchantRepository.save(merchantMapper.dtoToEntity(dto));

    return merchantMapper.entityToDto(merchant);
  }

  @Override
  public void delete(UUID merchantId) {
    Optional<Merchant> merchant =
        merchantRepository
            .findById(merchantId)
            .filter(m -> CollectionUtils.isEmpty(m.getTransactions()));

    merchant.ifPresent(merchantRepository::delete);
  }
}
