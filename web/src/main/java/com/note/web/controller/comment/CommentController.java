package com.note.web.controller.comment;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.common.HTTPCodeStatus;
import com.note.service.comment.CommentServices;
import com.note.web.controller.BaseController;
import com.note.web.entity.ResponseEntity;

/**
 * 
 * @author baixiaozheng
 * @Date 2016年1月6日 下午3:11:02
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(value = "/comment")
public class CommentController extends BaseController {

	@Resource(name = "commentService")
	private CommentServices commentService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "list")
	public ResponseEntity list(){
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, page, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
}
