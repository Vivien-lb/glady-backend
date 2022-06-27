package com.glady.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glady.backend.model.Deposit;
import com.glady.backend.model.GladyUser;
import com.glady.backend.repository.GladyUserRepository;

/**
 * The Class GladyUserService.
 */
@Service
public class GladyUserService {

	/** The glady user repository. */
	@Autowired
	private GladyUserRepository gladyUserRepository;

	/**
	 * Gets the user balance.
	 *
	 * @param id the id
	 * @return the user balance
	 */
	public BigDecimal getUserBalance(long id) {

		GladyUser gladyUser = gladyUserRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("GladyUser not found : " + id));

		BigDecimal result = BigDecimal.ZERO;

		// go through every user's deposit
		for (Deposit deposit : gladyUser.getDeposits()) {
			LocalDate receivedDate = deposit.getReceivedDate();
			LocalDate expireDate = deposit.getExpireDate();
			LocalDate now = LocalDate.now();

			// is deposit valid ?
			if (now.isAfter(receivedDate) && now.isBefore(expireDate))
				result = result.add(deposit.getAmount());

		}
		return result;
	}
}
