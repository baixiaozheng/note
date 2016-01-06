package com.note.service.comment.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.note.dao.comment.CommentDao;
import com.note.model.comment.Comment;
import com.note.service.comment.CommentServices;

/**
 * 
 * @author baixiaozheng
 * @Date 2016年1月6日 下午2:29:30
 */
@Service(value = "commentService")
public class CommentServiceImpl implements CommentServices {

	@Resource(name = "commentDao")
	private CommentDao commentDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.RuntimeException.class)
	public Comment add(Comment comment) {
		return commentDao.add(comment);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.RuntimeException.class)
	public Comment update(Comment comment) {
		return commentDao.update(comment);
	}

	@Override
	 @Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Comment getById(int id) {
		return commentDao.getById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.RuntimeException.class)
	public Comment audit(int id, Boolean publish) {
		Comment comment = commentDao.getById(id);
		comment.setPublish(publish);
		comment.setUpdateTime(new Date());
		return commentDao.update(comment);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.RuntimeException.class)
	public void delete(int id) {
		Comment comment = commentDao.getById(id);
		comment.setEnable(false);
		comment.setUpdateTime(new Date());
		commentDao.update(comment);
	}

	@Override
	 @Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Comment> listByNoteId(int noteId, Boolean enable, Boolean publish) {
		return commentDao.listByNoteId(noteId, enable, publish);
	}

}
