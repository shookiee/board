package kr.or.ddit.post.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class PostControllerTest extends ControllerTestEnv {

	/**
	* Method : postListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시글 리스트 화면 요청 테스트
	*/
	@Test
	public void postListTest() {
		/***Given***/

		/***When***/

		/***Then***/
	}

	
	/**
	* Method : readPostTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시글 상세 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void readPostTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/post/read")
													.param("postId", "1")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("post/readPost", viewName);
	}
	
	
	/**
	* Method : modifyPostViewTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시글 수정 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void modifyPostViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/post/modifyPost")
													.param("postId", "1")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("post/modify", viewName);
	}

}
