package me.trihung.services.impl;

import java.util.List;

import me.trihung.dao.ICategoryDao;
import me.trihung.dao.impl.CategoryDaoImpl;
import me.trihung.models.CategoryModel;
import me.trihung.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{
	
	ICategoryDao dao = new CategoryDaoImpl();
	
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void insert(CategoryModel categoryModel) {
		// TODO Auto-generated method stub
		dao.insert(categoryModel);
	}

	@Override
	public boolean update(CategoryModel categoryModel) {
		// TODO Auto-generated method stub
		return dao.update(categoryModel);
	}

	@Override
	public boolean delete(int id) {
		return dao.softDelete(null);
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		return dao.findName(keyword);
	}

	@Override
	public boolean softDelete(CategoryModel categoryModel) {
		return dao.softDelete(categoryModel);
	}
	
	

}
