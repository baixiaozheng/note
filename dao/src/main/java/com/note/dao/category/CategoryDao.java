package com.note.dao.category;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.note.dao.BaseDao;
import com.note.model.category.Category;

/**
 * 分类dao
 * @author baixiaozheng
 * @Date 2015年12月17日 下午11:43:37
 */
@SuppressWarnings("unchecked")
@Repository(value="categoryDao")
public class CategoryDao extends BaseDao {

	@Override
	protected Class<?> entityClass() {
		return Category.class;
	}

	/**
	 * 获取所有分类
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:43:49
	 */
	public List<Category> getAll(){
		return super.list();
	}
	
	/**
	 * 获取所有可用的分类
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:43:49 
	 */
	public List<Category> list(){
		String sql = "select * from category where enable=true";
		return (List<Category>) super.listEntityWithSql(sql);
	}
	
	/**
	 * 添加分类
	 * @param category
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:43:59
	 */
	public Category add(Category category){
		return super.add(category);
	}
	
	/**
	 * 根据id获取分类
	 * @param id
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:44:10
	 */
	public Category getById(int id){
		return super.get(id);
	}
	
	/**
	 * 更新分类
	 * @param category
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:44:14
	 */
	public Category update(Category category){
		return super.update(category);
	}
	
	/**
	 * 根据用户id查询可用的分类
	 * @param userId
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:44:17
	 */
	public List<Category> listByUserId(int userId){
		String sql = "select * from category where enable=:enable ";
		Map<String, Object> map = configMap(new String[]{"enable"}, new Object[]{true});
		if(userId > 0){
			sql += "and user_id=:userId";
			map.put("userId", userId);
		}
		return (List<Category>) super.listEntityWithSql(sql, map);
	}
}
 