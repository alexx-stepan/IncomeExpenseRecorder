package com.alexxstepan.dao.services;

import com.alexxstepan.dao.repositories.AccountRepository;
import com.alexxstepan.entities.Account;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository repository;

	public List<Account> getAll() {
		return IterableUtils.toList(repository.findAll());
	}

	public Optional<Account> get(long id) {
		return repository.findById(id);
	}

	public void save(Account account) {
		repository.save(account);
	}

	public void delete(Account account) {
		repository.delete(account);
	}
}
