package com.note.service.tag;

import java.util.List;

import com.note.model.tag.Tag;

/**
 * 标签service
 * 
 * @author baixiaozheng
 * @Date 2015年12月17日 下午11:33:24
 */
public interface TagServices {
	
	/**
	 * 获取所有的标签
	 * 
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:33:33
	 */
	List<Tag> list();

	/**
	 * 根据用户id查询可用标签
	 * 
	 * @param userId
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月3日 下午12:23:59
	 */
	List<Tag> listByUserId(int userId);
}
 