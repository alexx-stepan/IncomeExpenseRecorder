package com.alexxstepan.dao.services;

import com.alexxstepan.dao.repositories.TransactionRepository;
import com.alexxstepan.entities.Transaction;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	public List<Transaction> getAll() {
		return IterableUtils.toList(repository.findAll());
	}
}
