package com.note.web.controller.tag;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.common.HTTPCodeStatus;
import com.note.model.tag.Tag;
import com.note.model.user.User;
import com.note.service.tag.TagServices;
import com.note.web.controller.BaseController;
import com.note.web.entity.ResponseEntity;
import com.note.web.security.annotation.Authority;
import com.note.web.security.common.AuthorityType;
import com.note.web.security.util.SecurityUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(value = "/tag")
public class TagController extends BaseController {

	@Resource(name = "tagService")
	private TagServices tagService;
	

	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "list")
	public ResponseEntity list(){
		List<Tag> tags = tagService.list();
		page.setResult(tags);
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@Authority(type = AuthorityType.SECURITY)
	public ResponseEntity listByUser(){
		User user = SecurityUtil.currentLogin(request,response);
		int id = user.getId();
		List<Tag> tags = tagService.listByUserId(id);
		page.setResult(tags);
		page.setPageSize(tags.size());
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
}
 