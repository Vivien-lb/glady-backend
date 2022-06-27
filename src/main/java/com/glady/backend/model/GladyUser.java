package com.glady.backend.model;

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
public class GladyUser {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	String name;

	/** The deposits. */
	@OneToMany(mappedBy = "user")
	Set<Deposit> deposits;

	/**
	 * Instantiates a new glady user.
	 */
	public GladyUser() {
	}

	/**
	 * Instantiates a new glady user.
	 *
	 * @param name     the name
	 * @param deposits the deposits
	 */
	public GladyUser(String name, Set<Deposit> deposits) {
		this.name = name;
		this.deposits = deposits;
	}

}
