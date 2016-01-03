package com.note.dao.tag;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.note.dao.BaseDao;
import com.note.model.tag.Tag;

/**
 * 标签dao
 * @author baixiaozheng
 * @Date 2015年12月17日 下午11:54:42
 */
@Repository(value="tagDao")
public class TagDao extends BaseDao {

	@Override
	protected Class<?> entityClass() {
		return Tag.class;
	}

	/**
	 * 获取所有
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:54:54
	 */
	public List<Tag> getAll(){
		return super.list();
	}
	
	/**
	 * 查询所有可用标签
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:54:54
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> list(){
		String sql = "select * from tag where enable=true";
		return (List<Tag>) super.listEntityWithSql(sql);
	}
}
 