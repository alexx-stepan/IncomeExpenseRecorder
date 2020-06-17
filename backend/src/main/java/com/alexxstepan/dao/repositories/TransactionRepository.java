package com.alexxstepan.dao.repositories;

import com.alexxstepan.entities.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

	List<Transaction> findByAccountId(Long accountId);

	List<Transaction> findByAccountId(Long accountId, Pageable pageable);

	List<Transaction> findByBookingDateBetweenAndAmountGreaterThan(LocalDate from, LocalDate to, Integer amount);

	List<Transaction> findByBookingDateBetweenAndAccountIdAndAmountGreaterThan(LocalDate from, LocalDate to, Long accountId, Integer amount);

	List<Transaction> findByBookingDateBetweenAndAmountLessThan(LocalDate from, LocalDate to, Integer amount);

	List<Transaction> findByBookingDateBetweenAndAccountIdAndAmountLessThan(LocalDate from, LocalDate to, Long accountId, Integer amount);
}
