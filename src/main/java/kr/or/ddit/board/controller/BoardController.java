package kr.or.ddit.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	
	/**
	 * Method : manager
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 관리 메인 화면 요청
	 */
	@RequestMapping("/manager")
	public String boardManager() {
		return "board/board";
	}
	
	/**
	 * Method : addBoard
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 추가
	 */
	@RequestMapping(path = "/addBoard", method = RequestMethod.POST)
	public String addBoard(HttpSession session, BoardVO boardVo) {
		boardVo.setBoardId(boardService.boardMaxCnt());
		
		String userId = ((UserVO) session.getAttribute("USER_INFO")).getUserId();
		boardVo.setUserId(userId);
		
		if(boardService.addBoard(boardVo) == 1) {
			session.setAttribute("boardList", boardService.boardList());
			return "redirect:/main";
		} else {
			return "board/board";
		}
		
	}
	
	
	/**
	* Method : modifyBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param session
	* @param boardId
	* @param updateBoardName
	* @param updateUse_yn
	* @return
	* Method 설명 : 게시판 수정
	*/
	@RequestMapping(path = "/modifyBoard", method = RequestMethod.POST)
	public String modifyBoard(HttpSession session, int boardId, String updateBoardName, String updateUse_yn) {
		
		
		String userId = ((UserVO) session.getAttribute("USER_INFO")).getUserId();
		
		logger.debug("userId : {}", userId);
		logger.debug("boardName : {}", updateBoardName);
		logger.debug("use_yn : {}", updateUse_yn);
		
		BoardVO boardVo = new BoardVO(boardId, userId, updateBoardName, updateUse_yn);
		
		int updateCnt = boardService.modifyBoard(boardVo);
		
		
		if (updateCnt == 1) {
			List<BoardVO> boardList = boardService.boardList();
			session.setAttribute("boardList", boardList);
			return "redirect:/board/manager";
		} else {
			return "/main";
		}
		
	}

	
}
