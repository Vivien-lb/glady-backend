package com.glady.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glady.backend.dto.DepositDto;
import com.glady.backend.service.DepositService;

/**
 * The Class DepositController.
 */
@RestController
@RequestMapping("/deposit")
public class DepositController {

	/** The deposit service. */
	@Autowired
	private DepositService depositService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositController.class);

	/**
	 * Gift deposit.
	 *
	 * @param depositDto the deposit dto
	 * @return the response entity
	 */
	@PostMapping("/gift")
	public ResponseEntity<String> giftDeposit(@RequestBody DepositDto depositDto) {
		LOGGER.info("New gift deposit request : {}", depositDto);
		try {
			depositService.deposit(depositDto, true);
			return ResponseEntity.ok("Gift deposit created !");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * Meal deposit.
	 *
	 * @param depositDto the deposit dto
	 * @return the response entity
	 */
	@PostMapping("/meal")
	public ResponseEntity<String> mealDeposit(@RequestBody DepositDto depositDto) {
		LOGGER.info("New meal deposit request : {}", depositDto);
		try {
			depositService.deposit(depositDto, false);
			return ResponseEntity.ok("Meal deposit created !");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
