package com.alexxstepan.dao.repositories;

import com.alexxstepan.entities.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	// must use entity name instead of full table path (recorder.ACCOUNTS)
	// No need to use 'AndFlush' suffix as changes will be flushed after transaction commit
	@Transactional
	@Modifying
	@Query("UPDATE Account SET balance = balance + ?2 where id = ?1")
	void increaseBalanceFor(Long id, int amount);
}
