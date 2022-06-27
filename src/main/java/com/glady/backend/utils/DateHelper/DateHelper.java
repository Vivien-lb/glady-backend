package com.glady.backend.utils.DateHelper;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * The Class DateHelper.
 */
public class DateHelper {

	/**
	 * Instantiates a new date helper.
	 */
	private DateHelper() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Gets the gift expire date.
	 *
	 * @param receivedDate the received date
	 * @return the gift expire date
	 */
	public static LocalDate getGiftExpireDate(LocalDate receivedDate) {
		return receivedDate.plusDays(364);
	}

	/**
	 * Gets the meal expire date.
	 *
	 * @param receivedDate the received date
	 * @return the meal expire date
	 */
	public static LocalDate getMealExpireDate(LocalDate receivedDate) {
		LocalDate expireDate = receivedDate.plusYears(1).withMonth(2);
		YearMonth yearMonth = YearMonth.from(expireDate);
		expireDate = yearMonth.atEndOfMonth();

		return expireDate;
	}
}
