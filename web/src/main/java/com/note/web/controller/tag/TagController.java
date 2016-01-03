package com.note.web.controller.tag;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.common.HTTPCodeStatus;
import com.note.model.tag.Tag;
import com.note.service.tag.TagServices;
import com.note.web.controller.BaseController;
import com.note.web.entity.ResponseEntity;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(value = "/tag")
public class TagController extends BaseController {

	@Resource(name = "tagService")
	private TagServices tagService;
	

	@ResponseBody
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity list(){
		List<Tag> tags = tagService.list();
		page.setResult(tags);
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
	
}
 