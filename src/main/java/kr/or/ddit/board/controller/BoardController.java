package kr.or.ddit.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
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
	public String manager() {
		return "board/board";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	/**
	 * Method : addBoard
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 추가
	 */
	@RequestMapping("/addBoard")
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

}
