package com.alexxstepan.controllers;

import com.alexxstepan.dao.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountController {

	@Autowired
	private AccountService service;
}
