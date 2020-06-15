package com.alexxstepan.controllers;

import com.alexxstepan.dao.services.SummaryService;
import com.alexxstepan.entities.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/summary")
public class SummaryController {

	@Autowired
	private SummaryService service;

	public Map<String, List<Summary>> summary(@PathVariable LocalDate from,
											  @PathVariable LocalDate to,
											  @PathVariable(required = false) Long accountId) {
		Map<String, List<Summary>> result = new HashMap<>();

		result.put(
				"income",
				accountId == null ? service.getIncomeSummary() : service.getIncomeSummary(accountId));

		result.put(
				"expense",
				accountId == null ? service.getExpenseSummary() : service.getExpenseSummary(accountId));

		return result;
	}
}
