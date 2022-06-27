package com.glady.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.glady.backend.model.Company;
import com.glady.backend.model.Deposit;
import com.glady.backend.model.GladyUser;
import com.glady.backend.repository.GladyUserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class GladyUserServiceTest.
 */
@ExtendWith(MockitoExtension.class)
class GladyUserServiceTest {

	/** The glady user service. */
	@InjectMocks
	private GladyUserService gladyUserService;

	/** The glady user repository. */
	@Mock
	private GladyUserRepository gladyUserRepository;

	/**
	 * Gets the user balance test.
	 *
	 * @return the user balance test
	 */
	@Test
	void getUserBalanceTest() {
		Company company1 = new Company("Google", BigDecimal.valueOf(300), new HashSet<>());
		Company company2 = new Company("Tesla", BigDecimal.valueOf(300), new HashSet<>());
		GladyUser gladyUser = new GladyUser("Vivien", new HashSet<>());

		// 50$ - between the received and the expire date
		LocalDate receivDate1 = LocalDate.now().minusYears(1);
		LocalDate expireDate1 = LocalDate.now().plusYears(1);
		Deposit deposit1 = new Deposit(company1, gladyUser, BigDecimal.valueOf(50), receivDate1, expireDate1);

		// 100$ - expired
		LocalDate receivDate2 = LocalDate.now().minusYears(2);
		LocalDate expireDate2 = LocalDate.now().minusYears(1);
		Deposit deposit2 = new Deposit(company1, gladyUser, BigDecimal.valueOf(100), receivDate2, expireDate2);

		// 75$ - between the received and the expire date
		LocalDate receivDate3 = LocalDate.now().minusYears(1);
		LocalDate expireDate3 = LocalDate.now().plusYears(1);
		Deposit deposit3 = new Deposit(company2, gladyUser, BigDecimal.valueOf(75), receivDate3, expireDate3);

		company1.getDeposits().add(deposit1);
		company1.getDeposits().add(deposit2);
		company2.getDeposits().add(deposit3);
		gladyUser.getDeposits().add(deposit1);
		gladyUser.getDeposits().add(deposit2);
		gladyUser.getDeposits().add(deposit3);

		when(gladyUserRepository.findById(1L)).thenReturn(Optional.of(gladyUser));
		BigDecimal result = gladyUserService.getUserBalance(1);

		assertEquals(BigDecimal.valueOf(125), result);
	}

}
