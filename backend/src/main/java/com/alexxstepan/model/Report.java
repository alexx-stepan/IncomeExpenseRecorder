package com.alexxstepan.model;

import com.alexxstepan.serializers.ReportSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = ReportSerializer.class)
public class Report {

	private List<CategoryTotal> totalIncome;
	private List<CategoryTotal> totalExpense;

	public Report() {
		totalIncome = new ArrayList<>();
		totalExpense = new ArrayList<>();
	}

	public Report(List<CategoryTotal> totalIncome, List<CategoryTotal> totalExpense) {
		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
	}

	public void addTotal(String categoryName, int totalValue) {
		(totalValue < 0 ? totalExpense : totalIncome).add(new CategoryTotal(categoryName, totalValue));
	}

	public List<CategoryTotal> getTotalIncome() {
		return totalIncome;
	}

	public List<CategoryTotal> getTotalExpense() {
		return totalExpense;
	}
}
