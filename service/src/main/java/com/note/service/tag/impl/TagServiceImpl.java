package com.note.service.tag.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.note.dao.tag.TagDao;
import com.note.model.tag.Tag;
import com.note.service.tag.TagServices;

@Service(value = "tagService")
public class TagServiceImpl implements TagServices {

	@Resource(name = "tagDao")
	private TagDao tagDao;
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Tag> list() {
		return tagDao.list();
	}

}
 