package kr.or.ddit.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/delReply")
public class DeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyService();
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteReplyController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("deleteReplyController doGet()");
		logger.debug("replyId : {}", request.getParameter("replyId"));
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		ReplyVO replyVo = replyService.getReply(replyId);
		int postId = replyVo.getPostId();
		int deleteReply = replyService.deleteReply(replyId);
		
		if(deleteReply == 1) {
			response.sendRedirect(request.getContextPath() + "post?postId=" + postId);
		}
	}



}
