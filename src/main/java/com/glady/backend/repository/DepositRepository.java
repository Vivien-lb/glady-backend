package com.glady.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.glady.backend.model.Deposit;

/**
 * The Interface DepositRepository.
 */
public interface DepositRepository extends CrudRepository<Deposit, Long> {

}
