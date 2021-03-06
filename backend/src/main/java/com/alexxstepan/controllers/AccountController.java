package com.alexxstepan.controllers;

import com.alexxstepan.dao.services.AccountService;
import com.alexxstepan.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountService service;

	@RequestMapping("list")
	public List<Account> getAll() {
		return service.findAll();
	}

	@RequestMapping({"/{id}"})
	public Account get(/* @RequestParam */ @PathVariable long id) {
		Optional<Account> account = service.findById(id);

		return account.orElseThrow(() -> {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no account with ID " + id);
		});
	}

	@RequestMapping(method = RequestMethod.POST)
	public Account save(@RequestBody Account account) {
		return service.save(account);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Account update(@RequestBody Account account) {
		return service.save(account);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody Account account) {
		service.delete(account);
	}
}
