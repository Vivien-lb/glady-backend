package com.glady.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.glady.backend.dto.DepositDto;
import com.glady.backend.exceptions.NotEnoughMoneyException;
import com.glady.backend.model.Company;
import com.glady.backend.model.Deposit;
import com.glady.backend.model.GladyUser;
import com.glady.backend.repository.CompanyRepository;
import com.glady.backend.repository.DepositRepository;
import com.glady.backend.repository.GladyUserRepository;

/**
 * The Class DepositServiceTest.
 */
@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

	/** The deposit service. */
	@InjectMocks
	private DepositService depositService;

	/** The glady user repository. */
	@Mock
	private GladyUserRepository gladyUserRepository;

	/** The company repository. */
	@Mock
	private CompanyRepository companyRepository;

	/** The deposit repository. */
	@Mock
	private DepositRepository depositRepository;

	/**
	 * Test case where the company's balance doesn't allow the deposit
	 */
	@Test
	void depositBalanceNotEnoughTest() {

		Company company = new Company("Google", BigDecimal.valueOf(300), new HashSet<>());
		GladyUser gladyUser = new GladyUser("Vivien", new HashSet<>());
		when(gladyUserRepository.findById(1L)).thenReturn(Optional.of(gladyUser));
		when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

		// 350 > 300
		DepositDto deposit = new DepositDto(1, 1, BigDecimal.valueOf(350), LocalDate.now());
		// expected exception
		NotEnoughMoneyException exception = assertThrows(NotEnoughMoneyException.class, () -> {
			depositService.deposit(deposit, false);
		});

		assertEquals("Google company balance does not allow deposit!", exception.getMessage());
	}

	/**
	 * Test company not found
	 */
	@Test
	void depositCompanyNotFoundTest() {
		// no mock so the company is not found
		DepositDto deposit = new DepositDto(1, 1, BigDecimal.valueOf(350), LocalDate.now());
		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			depositService.deposit(deposit, false);
		});

		assertEquals("Company not found : 1", exception.getMessage());
	}

	/**
	 * OK test
	 */
	@Test
	void depositTest() throws NotEnoughMoneyException {
		Company company = new Company("Google", BigDecimal.valueOf(300), new HashSet<>());
		GladyUser gladyUser = new GladyUser("Vivien", new HashSet<>());
		when(gladyUserRepository.findById(1L)).thenReturn(Optional.of(gladyUser));
		when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
		// Return Deposit object passed to repository save method
		when(depositRepository.save(Mockito.any(Deposit.class))).thenAnswer(i -> i.getArguments()[0]);

		// 250$ deposit
		DepositDto depositDto = new DepositDto(1, 1, BigDecimal.valueOf(250), LocalDate.now());
		// meal deposit
		Deposit deposit = depositService.deposit(depositDto, false);

		assertEquals(BigDecimal.valueOf(250), deposit.getAmount());
	}
}
