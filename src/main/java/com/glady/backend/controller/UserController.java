package com.glady.backend.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glady.backend.service.GladyUserService;

/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/** The glady user service. */
	@Autowired
	GladyUserService gladyUserService;

	/**
	 * Gets the balance user.
	 *
	 * @param id the id
	 * @return the balance user
	 */
	@GetMapping("/userBalance/{id}")
	public ResponseEntity<String> getBalanceUser(@PathVariable long id) {
		try {
			BigDecimal userBalance = gladyUserService.getUserBalance(id);
			return ResponseEntity.ok("User balance is : " + userBalance.toString());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
