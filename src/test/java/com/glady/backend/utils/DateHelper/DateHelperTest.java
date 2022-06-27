package com.glady.backend.utils.DateHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * The Class DateHelperTest.
 */
class DateHelperTest {

	/**
	 * Gets the gift expire date test.
	 *
	 * @return the gift expire date test
	 */
	@Test
	void getGiftExpireDateTest() {
		LocalDate receivDate = LocalDate.of(2021, 06, 15);
		LocalDate expireDate = DateHelper.getGiftExpireDate(receivDate);

		LocalDate expected = LocalDate.of(2022, 06, 14);
		assertTrue(expireDate.isEqual(expected));
	}

	/**
	 * Gets the meal expire date test.
	 *
	 * @return the meal expire date test
	 */
	@Test
	void getMealExpireDateTest() {
		LocalDate receivDate = LocalDate.of(2020, 01, 01);
		LocalDate expireDate = DateHelper.getMealExpireDate(receivDate);

		LocalDate expected = LocalDate.of(2021, 02, 28);
		assertTrue(expireDate.isEqual(expected));
	}

}
