package com.alexxstepan.dao.services;

import com.alexxstepan.dao.repositories.AccountRepository;
import com.alexxstepan.entities.Account;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository repository;

	public List<Account> getAll() {
		return IterableUtils.toList(repository.findAll());
	}
}
