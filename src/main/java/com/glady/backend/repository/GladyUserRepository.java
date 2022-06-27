package com.glady.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.glady.backend.model.GladyUser;

/**
 * The Interface GladyUserRepository.
 */
public interface GladyUserRepository extends CrudRepository<GladyUser, Long> {

}
