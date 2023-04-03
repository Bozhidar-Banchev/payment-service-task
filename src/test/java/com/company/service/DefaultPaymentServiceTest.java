package com.company.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.company.dto.MerchantDto;
import com.company.exceprions.MerchantNotFoundException;
import com.company.model.Merchant;
import com.company.repository.MerchantRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DefaultPaymentServiceTest {

  @Mock private MerchantRepository merchantRepository;

  @InjectMocks private DefaultPaymentService paymentService;

  @Test
  void testFindAll() {
    // GIVEN
    Merchant merchant1 = Merchant.builder().name("Merchant1").build();
    Merchant merchant2 = Merchant.builder().name("Merchant2").build();
    List<Merchant> merchants = Arrays.asList(merchant1, merchant2);

    when(merchantRepository.findAll()).thenReturn(merchants);

    // WHEN
    List<MerchantDto> dtos = paymentService.findAll();

    // THEN
    assertEquals(2, dtos.size());
  }

  @Test
  void testFindAllWithNoMerchants() {
    // GIVEN
    when(merchantRepository.findAll()).thenReturn(Collections.emptyList());

    // WHEN
    List<MerchantDto> dtos = paymentService.findAll();

    // THEN
    assertEquals(Collections.emptyList(), dtos);
  }

  @Test
  void testFindByName() {
    // GIVEN
    Merchant merchant1 = Merchant.builder().name("Merchant1").build();

    when(merchantRepository.findByName(merchant1.getName())).thenReturn(Optional.of(merchant1));

    // WHEN
    MerchantDto response = paymentService.findByName(merchant1.getName());

    // THEN
    assertThat(response.getName()).isEqualTo(merchant1.getName());
  }

  @Test
  void testFindByName_NotFound() {
    // GIVEN
    String name = "name";
    when(merchantRepository.findByName(name)).thenReturn(Optional.empty());

    // THEN
    assertThatThrownBy(() -> paymentService.findByName("name"))
        .isInstanceOf(MerchantNotFoundException.class);
  }
}
