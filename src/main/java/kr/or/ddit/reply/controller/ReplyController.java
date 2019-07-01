package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyController.class);
	
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyService();
	}	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("replyController doPost");
		int postId = Integer.parseInt(request.getParameter("postId"));
		logger.debug("postId : {}", postId);
		UserVO userVo = (UserVO) request.getSession().getAttribute("USER_INFO");
		String userId = userVo.getUserId();
		logger.debug("userId : {}", userId);
		String replyContent = request.getParameter("replyContent");
		logger.debug("replyContent : {}", replyContent);
		
		ReplyVO replyVo = new ReplyVO();
		replyVo.setPostId(postId);
		replyVo.setReplyId(replyService.replyMaxCnt());
		replyVo.setReplyContent(replyContent);
		replyVo.setUserId(userId);
		
		logger.debug("replyVo : {}", replyVo);
		int insertReply = replyService.insertReply(replyVo);
		
		if(insertReply == 1) {
			List<ReplyVO> replyList = replyService.replyList(postId);
			request.setAttribute("replyList", replyList);

			response.sendRedirect(request.getContextPath() + "/read?postId=" + postId);
		}
	}

}
