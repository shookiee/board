package kr.or.ddit.board.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class BoardControllerTest extends ControllerTestEnv{

	/**
	* Method : boardManager
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시판 관리 메인 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void boardManager() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/board/manager")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("tiles.board", viewName);
	}
	
	
	/**
	 * Method : addBoardTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시판 추가 테스트
	 * @throws Exception 
	 */
	@Test
	public void addBoardTest() throws Exception {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUserId("brown");
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/board/addBoard")
													.sessionAttr("USER_INFO", userVo)
													.param("boardName", "테스트 게시판")
													.param("use_yn", "y")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("tiles.board", viewName);
	}

	
	/**
	* Method : modifyBoard
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시판 수정 테스트
	 * @throws Exception 
	*/
	@Test
	public void modifyBoard() throws Exception {
		/***Given***/
		String userId = "brown";
		UserVO userVo = new UserVO();
		userVo.setUserId(userId);

		int boardId = 1;
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/board/modifyBoard")
													.sessionAttr("USER_INFO", userVo)
													.param("boardId", "1")
													.param("updateBoardName", "공지사항")
													.param("updateUse_yn", "n")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("redirect:/board/manager", viewName);
	}

}
