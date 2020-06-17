package com.alexxstepan.model;

public class CategoryTotal {

	private String categoryName;
	private Integer value;

	public CategoryTotal(String categoryName, Integer value) {
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
