package com.company.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.dto.MerchantDto;
import com.company.dto.MerchantStatus;
import com.company.exceprions.MerchantNotFoundException;
import com.company.mapper.MerchantMapper;
import com.company.model.Merchant;
import com.company.repository.MerchantRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DefaultMerchantServiceTest {

  @Mock private MerchantMapper merchantMapper;

  @Mock private MerchantRepository merchantRepository;

  @InjectMocks private DefaultMerchantService merchantService;

  @Test
  void testFindAll() {
    // GIVEN
    Merchant merchant1 = Merchant.builder().name("Merchant1").build();
    Merchant merchant2 = Merchant.builder().name("Merchant2").build();
    List<Merchant> merchants = Arrays.asList(merchant1, merchant2);

    when(merchantRepository.findAll()).thenReturn(merchants);

    // WHEN
    List<MerchantDto> dtos = merchantService.findAll();

    // THEN
    assertEquals(2, dtos.size());
  }

  @Test
  void testFindAllWithNoMerchants() {
    // GIVEN
    when(merchantRepository.findAll()).thenReturn(Collections.emptyList());

    // WHEN
    List<MerchantDto> dtos = merchantService.findAll();

    // THEN
    assertEquals(Collections.emptyList(), dtos);
  }

  @Test
  void testFindByName() {
    // GIVEN
    Merchant merchant = Merchant.builder().name("Merchant").build();
    MerchantDto merchantDto = MerchantDto.builder().name("Merchant").build();

    when(merchantRepository.findByName(merchant.getName())).thenReturn(Optional.of(merchant));
    when(merchantMapper.entityToDto(merchant)).thenReturn(merchantDto);

    // WHEN
    MerchantDto response = merchantService.findByName(merchant.getName());

    // THEN
    assertThat(response.getName()).isEqualTo(merchant.getName());
  }

  @Test
  void testFindByName_NotFound() {
    // GIVEN
    String name = "name";
    when(merchantRepository.findByName(name)).thenReturn(Optional.empty());

    // THEN
    assertThatThrownBy(() -> merchantService.findByName("name"))
        .isInstanceOf(MerchantNotFoundException.class);
  }

  @Test
  void createMerchant() {
    // GIVEN
    MerchantDto merchantDto =
        MerchantDto.builder()
            .name("Merchant")
            .status(MerchantStatus.ACTIVE)
            .description("description")
            .build();
    Merchant merchant = Merchant.builder().name("Merchant").description("description").build();

    when(merchantMapper.dtoToEntity(merchantDto)).thenReturn(merchant);
    when(merchantMapper.entityToDto(merchant)).thenReturn(merchantDto);

    when(merchantRepository.save(merchant)).thenReturn(merchant);

    // WHEN
    MerchantDto resultDto = merchantService.create(merchantDto);

    // THEN
    assertEquals(merchantDto.getName(), resultDto.getName());
    assertEquals(merchantDto.getName(), resultDto.getName());
    verify(merchantMapper, times(1)).dtoToEntity(merchantDto);
    verify(merchantMapper, times(1)).entityToDto(merchant);
    verify(merchantRepository, times(1)).save(merchant);
  }

  @Test
  void deleteMerchantWithNoTransactions() {
    // GIVEN
    UUID merchantId = UUID.randomUUID();
    Merchant merchant =
        Merchant.builder().id(merchantId).name("Merchant").description("description").build();

    when(merchantRepository.findById(merchantId)).thenReturn(Optional.of(merchant));

    // WHEN
    merchantService.delete(merchantId);

    // THEN
    verify(merchantRepository, times(1)).delete(eq(merchant));
  }
}
