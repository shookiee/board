package kr.or.ddit.reply.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.reply.service.IReplyService;

@Controller
public class ReplyController {
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	
	@RequestMapping("/reply")
	public String reply() {
		return "";
	}
}
