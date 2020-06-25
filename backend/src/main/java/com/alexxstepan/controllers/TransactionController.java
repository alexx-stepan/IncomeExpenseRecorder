package com.alexxstepan.controllers;

import com.alexxstepan.dao.services.TransactionService;
import com.alexxstepan.entities.Transaction;
import com.alexxstepan.model.TransactionListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@RequestMapping("list")
	public List<Transaction> getAll(@RequestParam(required = false) Long accountId) {
		return accountId == null ? service.findAll() : service.findAll(accountId);
	}

	@RequestMapping(value = "list", params = {"pageNumber", "pageSize"})
	public TransactionListResponse getAll(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize,
			@RequestParam(required = false) Long accountId) {

		if (pageNumber == null && pageSize == null) {
			return accountId == null
					? new TransactionListResponse(service.findAll(), service.count())
					: new TransactionListResponse(service.findAll(accountId), service.countByAccountId(accountId));
		}

		if (pageNumber == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page number must be specified for pagination");
		}
		if (pageSize == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page size must be specified for pagination");
		}

		return accountId == null
				? new TransactionListResponse(service.findAll(pageNumber, pageSize), service.count())
				: new TransactionListResponse(service.findAll(accountId, pageNumber, pageSize), service.countByAccountId(accountId));
	}

	@RequestMapping("/{id}")
	public Transaction get(@PathVariable long id) {
		Optional<Transaction> transaction = service.find(id);

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
