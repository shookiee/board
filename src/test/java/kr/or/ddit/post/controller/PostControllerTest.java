package kr.or.ddit.post.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class PostControllerTest extends ControllerTestEnv {

	/**
	* Method : postListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시글 리스트 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void postListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/post/list")
										.param("boardId", "1")
										.param("page", "0")
										.param("pageSize", "0")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<PostVO> postList = (List<PostVO>) mav.getModelMap().get("postList");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		BoardVO boardVo = (BoardVO) mav.getModelMap().get("boardVo");
		
		/***Then***/
		assertEquals("post/post", viewName);
		assertEquals(10, postList.size());
		assertEquals(2, paginationSize);
		assertEquals("공지사항", boardVo.getBoardName());
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
		MvcResult mvcResult = mockMvc.perform(get("/post/readPost")
													.param("postId", "1")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("post/read", viewName);
	}
	
	
	/**
	 * Method : postFormViewTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 작성 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void postFormViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/post/postForm")
												.param("boardId", "1")
												.param("userId", "brown")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("post/postForm", viewName);
	}
	
	
	/**
	 * Method : postFormTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 작성 테스트
	 * @throws Exception 
	 */
	@Test
	public void postFormTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("uploadFile", f.getName(), "", new FileInputStream(f));

		/***When***/
		mockMvc.perform(fileUpload("/post/postForm").file(file).file(file).file(file)
																						.param("userId", "brown")
																						.param("boardId", "1")
																						.param("postTitle", "테스트")
																						.param("smarteditor", "테스트 내용")
																						.param("postId", "12"))
																						.andExpect(view().name("redirect:/post/readPost"));
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

	
	/**
	* Method : modifyPostTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시글 수정 테스트
	 * @throws Exception 
	*/
	@Test
	public void modifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("uploadFile", f.getName(), "", new FileInputStream(f));

		PostVO postVo = new PostVO();
		
		/***When***/
		mockMvc.perform(fileUpload("/post/modifyPost").file(file)
										.param("postId", "1")
										.param("postTitle", "Title")
										.param("smarteditor", "Content"))
										.andExpect(view().name("redirect:/post/readPost"));
		
		/***Then***/
	}
	
	
	/**
	* Method : deletePostTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 게시글 삭제 테스트
	 * @throws Exception 
	*/
	@Test
	public void deletePostTest() throws Exception {
		/***Given***/
		PostVO postVo = new PostVO();
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/post/deletePost")
													.param("postId", "1")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("redirect:/post/list", viewName);
	}
	
	
	/**
	* Method : answerPostViewTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 답글 작성 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void answerPostViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/post/answerPost")
													.param("postId", "1")).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("post/answer", viewName);
	}
	
	
	/**
	* Method : answerPostTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 답글 작성 테스트
	 * @throws Exception 
	*/
	@Test
	public void answerPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("uploadFile", f.getName(), "", new FileInputStream(f));

		/***When***/
		mockMvc.perform(fileUpload("/post/answerPost").file(file)
																						.param("postId", "1")
																						.param("userId", "brown")
																						.param("postTitle", "테스트")
																						.param("smarteditor", "테스트 내용"))
																						.andExpect(view().name("redirect:/post/readPost"));
	}
	
}
