package com.glady.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the expire date.
 *
 * @return the expire date
 */

/**
 * Gets the expire date.
 *
 * @return the expire date
 */
@Getter

/**
 * Sets the expire date.
 *
 * @param expireDate the new expire date
 */

/**
 * Sets the expire date.
 *
 * @param expireDate the new expire date
 */
@Setter
@Entity
public class Deposit {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	Company company;

	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	GladyUser user;

	/** The amount. */
	BigDecimal amount;

	/** The received date. */
	LocalDate receivedDate;

	/** The expire date. */
	LocalDate expireDate;

	/**
	 * Instantiates a new deposit.
	 */
	public Deposit() {
	}

	/**
	 * Instantiates a new deposit.
	 *
	 * @param company      the company
	 * @param user         the user
	 * @param amount       the amount
	 * @param receivedDate the received date
	 * @param expire       the expire
	 */
	public Deposit(Company company, GladyUser user, BigDecimal amount, LocalDate receivedDate, LocalDate expire) {
		this.company = company;
		this.user = user;
		this.amount = amount;
		this.receivedDate = receivedDate;
		this.expireDate = expire;
	}
}
