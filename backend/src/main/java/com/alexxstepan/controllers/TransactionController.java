package com.alexxstepan.controllers;

import com.alexxstepan.dao.services.TransactionService;
import com.alexxstepan.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@RequestMapping("list")
	public List<Transaction> getAll() {
		return service.getAll();
	}

	@RequestMapping("/{id}")
	public Transaction get(@PathVariable long id) {
		Optional<Transaction> transaction = service.get(id);

		return transaction.orElseThrow(() -> {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no transaction with ID " + id);
		});
	}

	@RequestMapping(method = RequestMethod.POST)
	public Transaction save(@RequestBody Transaction transaction) {
		return service.save(transaction);
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public Callable<Transaction> save(@RequestBody Transaction transaction) {
//		return () -> {
//			return service.save(transaction);
//		};
//	}

	@RequestMapping(method = RequestMethod.PUT)
	public Transaction update(@RequestBody Transaction transaction) {
		return service.save(transaction);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody Transaction transaction) {
		service.delete(transaction);
	}
}
