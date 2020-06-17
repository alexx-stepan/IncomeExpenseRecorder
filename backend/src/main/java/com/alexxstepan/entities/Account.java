package com.alexxstepan.entities;

import javax.persistence.*;

@Entity
@Table(schema = "recorder", name = "ACCOUNTS")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long accountNumber;
	private String ownerName;
	private String currencyCode;
	private Integer balance;

	public Account() {}
	public Account(Long accountNumber, String ownerName, String currencyCode, Integer balance) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.currencyCode = currencyCode;
		this.balance = balance;
	}

	/** GETTERS AND SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
}
