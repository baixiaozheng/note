package com.note.web.controller.note;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.common.HTTPCodeStatus;
import com.note.model.note.Category;
import com.note.model.user.User;
import com.note.service.note.CategoryServices;
import com.note.web.controller.BaseController;
import com.note.web.entity.ResponseEntity;
import com.note.web.security.annotation.Authority;
import com.note.web.security.common.AuthorityType;
import com.note.web.security.util.SecurityUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {

	@Resource(name = "categoryService")
	private CategoryServices categoryService;
	

	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity list(){
		List<Category> tags = categoryService.list();
		page.setResult(tags);
		page.setPageSize(tags.size());
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@Authority(type = AuthorityType.SECURITY)
	public ResponseEntity listByUser(){
		User user = SecurityUtil.currentLogin();
		int id = user.getId();
		
		return returnSuccess(null);
	}
}
 