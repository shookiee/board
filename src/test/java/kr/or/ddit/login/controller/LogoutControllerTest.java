package kr.or.ddit.login.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class LogoutControllerTest extends ControllerTestEnv {

	/**
	* Method : logoutTest
	* 작성자 : PC23
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인한 상황에서 로그인 뷰 요청 테스트
	*/
	@Test
	public void loginViewLoginedTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/logout").sessionAttr("USER_INFO", new UserVO())).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);

	}

}
