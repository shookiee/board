package kr.or.ddit.reply.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.reply.service.IReplyService;

@RequestMapping("/reply")
@Controller
public class ReplyController {
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	
	/**
	 * Method : reply
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@RequestMapping(path = "/addReply", method = RequestMethod.POST)
	public String addReply(int postId, String replyContent, HttpServletRequest request) {
	return "";
	}
}
