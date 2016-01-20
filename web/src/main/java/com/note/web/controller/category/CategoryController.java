package com.note.web.controller.category;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.common.HTTPCodeStatus;
import com.note.model.category.Category;
import com.note.model.user.User;
import com.note.service.category.CategoryServices;
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
    @RequestMapping(method = RequestMethod.GET, value = "list")
	public ResponseEntity list(){
		List<Category> categories = categoryService.list();
		page.setResult(categories);
		page.setPageSize(categories.size());
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@Authority(type = AuthorityType.SECURITY)
	public ResponseEntity listByUser(){
		User user = SecurityUtil.currentLogin(request,response);
		int id = user.getId();
		List<Category> categories = categoryService.listByUserId(id);
		page.setResult(categories);
		page.setPageSize(categories.size());
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
}
 