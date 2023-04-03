package com.company.controlller;

import com.company.dto.MerchantDto;
import com.company.service.DefaultMerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Validated
@RestController
@RequestMapping("api/merchant")
@RequiredArgsConstructor
public class MerchantController {
  private final DefaultMerchantService service;

  @Operation(summary = "Find all merchants.")
  @ApiResponse(responseCode = "200", description = "Merchants found.")
  @GetMapping()
  public List<MerchantDto> findAllMerchant() {

    return service.findAll();
  }

  @Operation(summary = "Find Merchant by name.")
  @ApiResponse(responseCode = "200", description = "Merchant found.")
  @ApiResponse(
      responseCode = "404",
      description = "Merchant not found.",
      content = @Content(schema = @Schema(implementation = Problem.class)))
  @GetMapping("/{name}")
  public MerchantDto findMerchantByName(@PathVariable String name) {

    return service.findByName(name);
  }

  @Operation(summary = "Find all merchants.")
  @ApiResponse(responseCode = "200", description = "Merchant created.")
  @ApiResponse(
      responseCode = "400",
      description = "Invalid object supplied.",
      content = @Content(schema = @Schema(implementation = Problem.class)))
  @PostMapping("/create")
  public MerchantDto createMerchant(@RequestBody MerchantDto dto) {

    return service.create(dto);
  }

  @Operation(summary = "Find all merchants.")
  @ApiResponse(responseCode = "204", description = "Merchant deleted.")
  @DeleteMapping("/{id}")
  public void removeMerchant(@PathVariable UUID id) {

    service.delete(id);
  }
}
