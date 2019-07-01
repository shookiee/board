package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class BoardControllerTest extends ControllerTestEnv{

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
		assertEquals("redirect:/main", viewName);
	}

}
