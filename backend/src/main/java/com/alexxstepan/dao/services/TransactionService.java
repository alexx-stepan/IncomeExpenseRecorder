package com.alexxstepan.dao.services;

import com.alexxstepan.dao.repositories.TransactionRepository;
import com.alexxstepan.entities.Transaction;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
//@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository repository;
	@Autowired
	private AccountService accountService;

	private Lock lock;

	@PostConstruct
	public void init() {
		lock = new ReentrantLock();
	}

	public List<Transaction> findAll() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<Transaction> findAll(int page, int size) {
		return IterableUtils.toList(repository.findAll(PageRequest.of(page, size)));
	}

	public List<Transaction> findAll(Long accountId) {
		return IterableUtils.toList(repository.findByAccountId(accountId));
	}

	public List<Transaction> findAll(Long accountId, int page, int size) {
		return IterableUtils.toList(repository.findByAccountId(accountId, PageRequest.of(page, size)));
	}

	public List<Transaction> findAllGreaterThan(LocalDate from, LocalDate to, Integer amount) {
		return IterableUtils.toList(repository.findByBookingDateBetweenAndAmountGreaterThan(from, to, amount));
	}

	public List<Transaction> findAllGreaterThan(LocalDate from, LocalDate to, Long accountId, Integer amount) {
		return IterableUtils.toList(repository.findByBookingDateBetweenAndAccountIdAndAmountGreaterThan(from, to, accountId, amount));
	}

	public List<Transaction> findAllLessThan(LocalDate from, LocalDate to, Integer amount) {
		return IterableUtils.toList(repository.findByBookingDateBetweenAndAmountLessThan(from, to, amount));
	}

	public List<Transaction> findAllLessThan(LocalDate from, LocalDate to, Long accountId, Integer amount) {
		return IterableUtils.toList(repository.findByBookingDateBetweenAndAccountIdAndAmountLessThan(from, to, accountId, amount));
	}

	public Optional<Transaction> find(long id) {
		return repository.findById(id);
	}

	public Transaction save(Transaction transaction) {
		Long accountId = transaction.getAccount().getId();

		lock.lock();

		Transaction saved = repository.save(transaction);
		accountService.increaseBalanceFor(accountId, transaction.getAmount());
		saved.setAccount(accountService.findById(accountId).get());

		lock.unlock();

		return saved;
	}

	public long count() {
		return repository.count();
	}

	public long countByAccountId(Long accountId) {
		return repository.countByAccountId(accountId);
	}

	public void delete(Transaction transaction) {
		repository.delete(transaction);
	}
}
