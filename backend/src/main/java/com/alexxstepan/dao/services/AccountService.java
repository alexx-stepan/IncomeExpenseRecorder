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
//@Transactional
public class AccountService {

	@Autowired
	private AccountRepository repository;

	public List<Account> findAll() {
		return IterableUtils.toList(repository.findAll());
	}

	public Optional<Account> findById(long id) {
		return repository.findById(id);
	}

	public Account save(Account account) {
		return repository.save(account);
	}

	void increaseBalanceFor(Long id, int amount) {
		repository.increaseBalanceFor(id, amount);
	}

	public void delete(Account account) {
		repository.delete(account);
	}
}
