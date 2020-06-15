package com.alexxstepan.dao.repositories;

import com.alexxstepan.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByAccountId(Long accountId);

	List<Transaction> findByAmountGreaterThan(Integer amount);

	List<Transaction> findByAccountIdAndAmountGreaterThan(Long accountId, Integer amount);

	List<Transaction> findByAmountLessThan(Integer amount);

	List<Transaction> findByAccountIdAndAmountLessThan(Long accountId, Integer amount);
}
