package com.alexxstepan.dao.triggers;

import com.alexxstepan.dao.ApplicationContextListener;
import com.alexxstepan.dao.repositories.AccountRepository;
import com.alexxstepan.entities.Account;
import org.h2.api.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAccountBalanceTrigger /* implements Trigger */ {

	private AccountRepository accountRepository;

//	@Override
	public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {
	}

//	@Override
	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
		if (accountRepository == null) {
			accountRepository = ApplicationContextListener.getBean(AccountRepository.class);
		}

		Account account = accountRepository.findById(((Integer) newRow[1]).longValue()).get();
		account.setBalance(account.getBalance() + (Integer) newRow[4]);
		accountRepository.save(account);
	}

//	@Override
	public void close() throws SQLException {
	}

//	@Override
	public void remove() throws SQLException {
	}
}
