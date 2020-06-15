package com.alexxstepan.dao.services;

import com.alexxstepan.entities.Summary;
import com.alexxstepan.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryService {

	@Autowired
	private TransactionService transactionService;

	public List<Summary> getIncomeSummary() {
		List<Transaction> transactions = transactionService.getAllGreaterThan(0);

		return convertToSummary(transactions);
	}

	public List<Summary> getIncomeSummary(Long accountId) {
		List<Transaction> transactions = transactionService.getAllGreaterThan(accountId, 0);

		return convertToSummary(transactions);
	}

	public List<Summary> getExpenseSummary() {
		List<Transaction> transactions = transactionService.getAllLessThan(0);

		return convertToSummary(transactions);
	}

	public List<Summary> getExpenseSummary(Long accountId) {
		List<Transaction> transactions = transactionService.getAllLessThan(accountId, 0);

		return convertToSummary(transactions);
	}

	private List<Summary> convertToSummary(List<Transaction> transactions) {
		return transactions
				.stream()
				.collect(Collectors.groupingBy(Transaction::getCategoryName, Collectors.summingInt(Transaction::getAmount)))
				.entrySet()
				.stream()
				.map(entry -> new Summary(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}
}
