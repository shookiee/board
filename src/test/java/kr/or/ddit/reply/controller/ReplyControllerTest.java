package kr.or.ddit.reply.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class ReplyControllerTest extends ControllerTestEnv {

	/**
	* Method : addReplyTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 댓글 등록 테스트
	 * @throws Exception 
	*/
	@Test
	public void addReplyTest() throws Exception {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUserId("brown");
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/reply/addReply")
													.sessionAttr("USER_INFO", userVo)
													.param("postId", "1")
													.param("replyContent", "댓글테스트")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("redirect:/post/readPost", viewName);
	}

	
	/**
	* Method : deleteReplyTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 댓글 삭제 테스트
	 * @throws Exception 
	*/
	@Test
	public void deleteReplyTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/reply/deleteReply")
													.param("replyId", "1")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("redirect:/post/readPost", viewName);
	}
}
