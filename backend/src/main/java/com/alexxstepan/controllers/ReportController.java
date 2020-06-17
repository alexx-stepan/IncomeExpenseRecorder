package com.alexxstepan.controllers;

import com.alexxstepan.dao.services.ReportService;
import com.alexxstepan.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/report")
public class ReportController {

	@Autowired
	private ReportService service;

	@RequestMapping
	public Report get(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
			@RequestParam(required = false) Long accountId) {

		return service.getReport(from, to, accountId);
	}
}
