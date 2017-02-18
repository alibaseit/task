package com.dogan.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dogan.model.LoginAccount;

@Repository
public interface LoginAccountRepository extends CrudRepository<LoginAccount, Long> {
	public LoginAccount findByUsername(String username);
}
