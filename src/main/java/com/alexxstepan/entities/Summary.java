package com.alexxstepan.entities;

import com.alexxstepan.SummarySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = SummarySerializer.class)
public class Summary {

	private String categoryName;
	private Integer value;

	public Summary(String categoryName, Integer value) {
		this.categoryName = categoryName;
		this.value = value;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Integer getValue() {
		return value;
	}
}
