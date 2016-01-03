package com.note.dao.tag;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.note.dao.BaseDao;
import com.note.model.tag.Tag;

/**
 * 标签dao
 * @author baixiaozheng
 * @Date 2015年12月17日 下午11:54:42
 */
@SuppressWarnings("unchecked")
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
	public List<Tag> list(){
		String sql = "select * from tag where enable=true";
		return (List<Tag>) super.listEntityWithSql(sql);
	}
	
	/**
	 * 根据用户id查询可用的标签
	 * 
	 * @param userId
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月3日 下午12:22:56
	 */
	public List<Tag> listByUserId(int userId){
		String sql = "select * from tag where enable=:enable ";
		Map<String, Object> map = configMap(new String[]{"enable"}, new Object[]{true});
		if(userId > 0){
			sql += "and user_id=:userId";
			map.put("userId", userId);
		}
		return (List<Tag>) super.listEntityWithSql(sql, map);
	}
}
 