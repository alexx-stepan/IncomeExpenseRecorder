package com.alexxstepan.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "recorder", name = "TRANSACTIONS")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Account account;
	private String categoryName;
	private LocalDateTime bookingDate;
	private Integer amount;

	public Transaction() {}
	public Transaction(Account account, String categoryName, LocalDateTime bookingDate, Integer amount) {
		this.account = account;
		this.categoryName = categoryName;
		this.bookingDate = bookingDate;
		this.amount = amount;
	}

	/** GETTERS AND SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
