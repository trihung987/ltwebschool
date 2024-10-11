package me.trihung.services;

import java.util.List;

import me.trihung.models.CategoryModel;

public interface ICategoryService {
	
	List<CategoryModel> findAll();
	
	CategoryModel findById(int id);
	
	void insert(CategoryModel categoryModel);
	
	boolean update(CategoryModel categoryModel);
	
	boolean delete(int id);
	
	List<CategoryModel> findName(String keyword);
	
	boolean softDelete(CategoryModel categoryModel);
	
}
