package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ModifyBoardController.class);
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ModifyBoardController doPost()");
		
		request.setCharacterEncoding("UTF-8");
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		UserVO userVo = (UserVO) request.getSession().getAttribute("USER_INFO");
		String userId = userVo.getUserId();
		
		String boardName = request.getParameter("updateBoardName");
		String use_yn = request.getParameter("updateUse_yn");
		
		logger.debug("boardId : {}", boardId);
		logger.debug("userId : {}", userId);
		logger.debug("boardName : {}", boardName);
		logger.debug("use_yn : {}", use_yn);
		
		BoardVO boardVo = new BoardVO(boardId, userId, boardName, use_yn);
		
		int updateCnt = boardService.modifyBoard(boardVo);
		
		
		if (updateCnt == 1) {
			List<BoardVO> boardList = boardService.boardList();
			request.getSession().setAttribute("boardList", boardList);
			response.sendRedirect(request.getContextPath() + "/board");
		}
	}

}
