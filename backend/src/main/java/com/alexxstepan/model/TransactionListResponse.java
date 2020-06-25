package com.alexxstepan.model;

import com.alexxstepan.entities.Transaction;

import java.util.List;

public class TransactionListResponse {

	private List<Transaction> transactionsOnPage;
	private Long transactionTotalNumber;

	public TransactionListResponse() {}

	public TransactionListResponse(List<Transaction> transactionsOnPage, Long transactionTotalNumber) {
		this.transactionsOnPage = transactionsOnPage;
		this.transactionTotalNumber = transactionTotalNumber;
	}

	public List<Transaction> getTransactionsOnPage() {
		return transactionsOnPage;
	}

	public void setTransactionsOnPage(List<Transaction> transactionsOnPage) {
		this.transactionsOnPage = transactionsOnPage;
	}

	public Long getTransactionTotalNumber() {
		return transactionTotalNumber;
	}

	public void setTransactionTotalNumber(Long transactionTotalNumber) {
		this.transactionTotalNumber = transactionTotalNumber;
	}
}
