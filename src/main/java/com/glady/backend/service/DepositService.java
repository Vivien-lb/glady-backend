package com.glady.backend.service;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glady.backend.dto.DepositDto;
import com.glady.backend.exceptions.NotEnoughMoneyException;
import com.glady.backend.model.Company;
import com.glady.backend.model.Deposit;
import com.glady.backend.model.GladyUser;
import com.glady.backend.repository.CompanyRepository;
import com.glady.backend.repository.DepositRepository;
import com.glady.backend.repository.GladyUserRepository;
import com.glady.backend.utils.DateHelper.DateHelper;

/**
 * The Class DepositService.
 */
@Service
public class DepositService {

	/** The deposit repository. */
	@Autowired
	private DepositRepository depositRepository;

	/** The company repository. */
	@Autowired
	private CompanyRepository companyRepository;

	/** The glady user repository. */
	@Autowired
	private GladyUserRepository gladyUserRepository;

	/**
	 * Method to apply a gift or a meal deposit from a company to a user.
	 *
	 * @param depositDto the deposit dto
	 * @param isGift     the is gift
	 * @throws NotEnoughMoneyException the not enough money exception
	 */
	public Deposit deposit(DepositDto depositDto, boolean isGift) throws NotEnoughMoneyException {
		long companyId = depositDto.getCompanyId();
		long userId = depositDto.getGladyUserId();

		// get company
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new EntityNotFoundException("Company not found : " + companyId));

		// get user
		GladyUser gladyUser = gladyUserRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("GladyUser not found : " + userId));

		// Company doesn't have enough money
		if (company.getBalance().compareTo(depositDto.getAmount()) < 0)
			throw new NotEnoughMoneyException(company.getName());

		// calcul of expiration date
		LocalDate receivedDate = depositDto.getReceivedDate();
		LocalDate expireDate = isGift ? DateHelper.getGiftExpireDate(receivedDate)
				: DateHelper.getMealExpireDate(receivedDate);

		Deposit deposit = new Deposit(company, gladyUser, depositDto.getAmount(), receivedDate, expireDate);

		depositRepository.save(deposit);
		// remove deposit amount from company balance
		company.setBalance(company.getBalance().add(depositDto.getAmount().negate()));
		companyRepository.save(company);

		return deposit;
	}

}
