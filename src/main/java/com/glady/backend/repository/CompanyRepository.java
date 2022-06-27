package com.glady.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.glady.backend.model.Company;

/**
 * The Interface CompanyRepository.
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
