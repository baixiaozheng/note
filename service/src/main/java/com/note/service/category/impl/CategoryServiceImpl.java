package com.note.service.category.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.note.dao.category.CategoryDao;
import com.note.model.category.Category;
import com.note.service.category.CategoryServices;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryServices {

	@Resource(name = "categoryDao")
	private CategoryDao categoryDao;
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Category> list() {
		return categoryDao.list();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Category> listByUserId(int userId) {
		return categoryDao.listByUserId(userId);
	}
}
 