package com.glady.backend.exceptions;

/**
 * The Class NotEnoughMoneyException.
 */
@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {

	/**
	 * Instantiates a new not enough money exception.
	 *
	 * @param companyName the company name
	 */
	public NotEnoughMoneyException(String companyName) {
		super(companyName + " company balance does not allow deposit!");
	}
}
