package kr.or.ddit.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteController.class);
	
	private IPostService postService;
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		replyService = new ReplyService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("DeleteController doGet()");
		int postId = Integer.parseInt(request.getParameter("postId"));
		PostVO postVo = postService.getPost(postId);
		int boardId = postVo.getBoardId();

		int deleteCnt = postService.deletePost(postId);

		
		if(deleteCnt == 1) {
			int delReplyCnt = replyService.delReplyCnt(postId);
			response.sendRedirect(request.getContextPath() + "/post?boardId=" + boardId);
		}
		
	}


}
 