package com.note.dao.comment;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.note.dao.BaseDao;
import com.note.model.comment.Comment;

/**
 * 评论dao
 * 
 * @author baixiaozheng
 * @Date 2016年01月06日11:40:05
 */
@Repository(value = "commentDao")
public class CommentDao extends BaseDao {

	@Override
	protected Class<?> entityClass() {
		return Comment.class;
	}

	/**
	 * 添加
	 * 
	 * @param comment
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 上午11:48:50
	 */
	public Comment add(Comment comment) {
		return super.add(comment);
	}

	/**
	 * 更新
	 * 
	 * @param comment
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 上午11:50:15
	 */
	public Comment update(Comment comment) {
		return super.update(comment);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 上午11:50:59
	 */
	public Comment getById(int id) {
		return super.get(id);
	}

	/**
	 * 根据noteId查询
	 * 
	 * @param noteId
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午12:11:27
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> listByNoteId(int noteId, Boolean enable, Boolean publish) {
		Map<String, Object> map = configMap(new String[] { "noteId", "enable", "publish" }, new Object[] { noteId, enable, publish });
		String sql = "select * from comment where note_id=:noteId and enable=:enable and publish=:publish ";
		return (List<Comment>) super.listEntityWithSql(sql, map);
	}

	/**
	 * 根据父id查询
	 * 
	 * @param parentId
	 * @return
	 * @author baixiaozheng
	 * @Date 2016年1月6日 下午12:08:41
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> listByParentId(int parentId, Boolean enable, Boolean publish) {
		Map<String, Object> map = configMap(new String[] { "parentId", "enable", "publish" }, new Object[] { parentId, enable, publish });
		String sql = "select * from comment where parent_id=:parentId and enable=:enable and publish=:publish  ";
		return (List<Comment>) super.listEntityWithSql(sql, map);
	}
}
