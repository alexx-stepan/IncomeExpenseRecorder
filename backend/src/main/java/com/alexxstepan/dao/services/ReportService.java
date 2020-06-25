package com.alexxstepan.dao.services;

import com.alexxstepan.model.CategoryTotal;
import com.alexxstepan.model.Report;
import com.alexxstepan.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

	@Autowired
	private TransactionService transactionService;

	public List<CategoryTotal> getTotalIncome(LocalDate from, LocalDate to) {
		List<Transaction> transactions = transactionService.findAllGreaterThan(from, to, 0);

		return convertToTotal(transactions);
	}

	public List<CategoryTotal> getTotalIncome(LocalDate from, LocalDate to, Long accountId) {
		List<Transaction> transactions = transactionService.findAllGreaterThan(from, to, accountId, 0);

		return convertToTotal(transactions);
	}

	public List<CategoryTotal> getTotalExpense(LocalDate from, LocalDate to) {
		List<Transaction> transactions = transactionService.findAllLessThan(from, to, 0);

		return convertToTotal(transactions);
	}

	public List<CategoryTotal> getTotalExpense(LocalDate from, LocalDate to, Long accountId) {
		List<Transaction> transactions = transactionService.findAllLessThan(from, to, accountId, 0);

		return convertToTotal(transactions);
	}

	public Report getReport(LocalDate from, LocalDate to, Long accountId) {
		List<CategoryTotal> totalIncome = accountId == null ? getTotalIncome(from, to) : getTotalIncome(from, to, accountId);
		List<CategoryTotal> totalExpense = accountId == null ? getTotalExpense(from, to) : getTotalExpense(from, to, accountId);

		return new Report(totalIncome, totalExpense);
	}

	private List<CategoryTotal> convertToTotal(List<Transaction> transactions) {
		return transactions
				.stream()
				.collect(Collectors.groupingBy(Transaction::getCategoryName, Collectors.summingInt(Transaction::getAmount)))
				.entrySet()
				.stream()
				.map(entry -> new CategoryTotal(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}
}
