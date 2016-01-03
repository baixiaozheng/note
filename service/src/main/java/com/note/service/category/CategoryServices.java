package com.note.service.category;

import java.util.List;

import com.note.model.category.Category;

/**
 * 类别service
 * 
 * @author baixiaozheng
 * @Date 2015年12月17日 下午11:29:32
 */
public interface CategoryServices {

	/**
	 * 获取所有的标签
	 * 
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:29:12
	 */
	List<Category> list();

	/**
	 * 根据userId查询标签
	 * 
	 * @param userId
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月3日 下午12:09:51
	 */
	List<Category> listByUserId(int userId);
}
 