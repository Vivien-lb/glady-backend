package com.glady.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * Deposit DTO
 */
@Data
public class DepositDto {

	/** The glady user id. */
	long gladyUserId;

	/** The company id. */
	long companyId;

	/** The amount. */
	BigDecimal amount;

	/** The received date. */
	LocalDate receivedDate;

	/**
	 * Instantiates a new deposit dto.
	 *
	 * @param gladyUserId  the glady user id
	 * @param companyId    the company id
	 * @param amount       the amount
	 * @param receivedDate the received date
	 */
	public DepositDto(long gladyUserId, long companyId, BigDecimal amount, LocalDate receivedDate) {
		this.gladyUserId = gladyUserId;
		this.companyId = companyId;
		this.amount = amount;
		this.receivedDate = receivedDate;
	}
}
