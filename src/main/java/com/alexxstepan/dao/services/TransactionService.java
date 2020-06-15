package com.alexxstepan.dao.services;

import com.alexxstepan.dao.repositories.TransactionRepository;
import com.alexxstepan.entities.Transaction;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	public List<Transaction> getAll() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<Transaction> getAll(Long accountId) {
		return IterableUtils.toList(repository.findByAccountId(accountId));
	}

	public List<Transaction> getAllGreaterThan(Integer amount) {
		return IterableUtils.toList(repository.findByAmountGreaterThan(amount));
	}

	public List<Transaction> getAllGreaterThan(Long accountId, Integer amount) {
		return IterableUtils.toList(repository.findByAccountIdAndAmountGreaterThan(accountId, amount));
	}

	public List<Transaction> getAllLessThan(Integer amount) {
		return IterableUtils.toList(repository.findByAmountLessThan(amount));
	}

	public List<Transaction> getAllLessThan(Long accountId, Integer amount) {
		return IterableUtils.toList(repository.findByAccountIdAndAmountLessThan(accountId, amount));
	}

	public Optional<Transaction> get(long id) {
		return repository.findById(id);
	}

	public void save(Transaction transaction) {
		repository.save(transaction);
	}

	public void delete(Transaction transaction) {
		repository.delete(transaction);
	}
}
