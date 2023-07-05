package com.exam.portal.services;

import java.util.Set;

import com.exam.portal.entites.exam.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category udateCategory(Category category);
	
	public Set<Category> getCategories();
	
	public Category getCategory(Long categoryId);
	
	public void deleteCategory(Long categoryId);
}
