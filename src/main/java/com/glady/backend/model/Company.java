package com.glady.backend.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the deposits.
 *
 * @return the deposits
 */

/**
 * Gets the deposits.
 *
 * @return the deposits
 */
@Getter

/**
 * Sets the deposits.
 *
 * @param deposits the new deposits
 */

/**
 * Sets the deposits.
 *
 * @param deposits the new deposits
 */
@Setter
@Entity
public class Company {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	String name;

	/** The balance. */
	BigDecimal balance;

	/** The deposits. */
	@OneToMany(mappedBy = "company")
	Set<Deposit> deposits;

	/**
	 * Instantiates a new company.
	 */
	public Company() {
	}

	/**
	 * Instantiates a new company.
	 *
	 * @param name     the name
	 * @param balance  the balance
	 * @param deposits the deposits
	 */
	public Company(String name, BigDecimal balance, Set<Deposit> deposits) {
		this.name = name;
		this.balance = balance;
		this.deposits = deposits;
	}
}
