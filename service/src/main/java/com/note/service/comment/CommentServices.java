package com.note.service.comment;

import java.util.List;

import com.note.model.comment.Comment;

/**
 * 评论service
 * 
 * @author baixiaozheng
 * @Date 2016年1月6日 下午12:02:04
 */
public interface CommentServices {

	/**
	 * 添加
	 * 
	 * @param comment
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午2:28:06
	 */
	Comment add(Comment comment);
	
	/**
	 * 更新
	 * 
	 * @param comment
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午2:28:13
	 */
	Comment update(Comment comment);
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午2:28:21
	 */
	Comment getById(int id);
	
	/**
	 * 审核
	 * 
	 * @param id
	 * @param publish
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午2:28:29
	 */
	Comment audit(int id, Boolean publish);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午2:28:38
	 */
	void delete(int id);
	
	/**
	 * 根据noteId查询评论列表
	 * 
	 * @param noteId
	 * @param enable
	 * @param publish
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午2:33:22
	 */
	List<Comment> listByNoteId(int noteId, Boolean enable, Boolean publish);
}
