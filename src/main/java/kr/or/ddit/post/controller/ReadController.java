package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.AttachFileService;
import kr.or.ddit.attachFile.service.IAttachFileService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/read")
public class ReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IPostService postService;
	private IAttachFileService fileService;
	private IBoardService boardService;
	private IReplyService replyService;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		fileService = new AttachFileService();
		boardService = new BoardService();
		replyService = new ReplyService();
	}

	private static final Logger logger = LoggerFactory
			.getLogger(ReadController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postId = Integer.parseInt(request.getParameter("postId"));

		PostVO postVo = postService.getPost(postId);
		
		List<AttachFileVO> fileList = fileService.getFileList(postId);
		List<BoardVO> boardList = boardService.boardList();
		request.setAttribute("postVo", postVo);
		request.setAttribute("fileList", fileList);
		request.setAttribute("board", boardList);

		int boardId = postVo.getBoardId();
		BoardVO boardVo = boardService.getBoard(boardId);
		
		request.setAttribute("boardVo", boardVo);

		List<ReplyVO> replyList = replyService.replyList(postId);
		logger.debug("replyList : {}", replyList);
		request.setAttribute("replyList", replyList);
		
		request.getRequestDispatcher("/post/read.jsp").forward(request, response);
		
	}

}
