package br.com.wallissonalves.picpay.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransferDto(
		@DecimalMin("0.01") @NotNull BigDecimal value,
		@NotNull Long payee,
		@NotNull Long payer) {}