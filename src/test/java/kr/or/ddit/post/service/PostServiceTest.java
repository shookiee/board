package kr.or.ddit.post.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class PostServiceTest extends LogicTestEnv {

	@Resource(name = "postService")
	private IPostService postService;
	
	
	/**
	 * Method : allPostListTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 전체 게시글 리스트 조회 테스트
	 */
	@Test
	public void allPostListTest() {
		/***Given***/

		/***When***/
		List<PostVO> postList = postService.allPostList();
		
		/***Then***/
		assertEquals(11, postList.size());
		assertEquals("brown", postList.get(0).getUserId());
	}

	
	/**
	 * Method : boardPostListTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 특정 게시판 게시글 조회 테스트
	 */
	@Test
	public void boardPostListTest() {
		/***Given***/
		int boardId = 1;
		
		/***When***/
		List<PostVO> boardPostList =postService.boardPostList(boardId);
		
		/***Then***/
		assertEquals(11, boardPostList.size());
	}
	
	
	/**
	 * Method : postPagingListTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 페이징 리스트 테스트
	 */
	@Test
	public void postPagingListTest() {
		/***Given***/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId", 1);
		map.put("page", 1);
		map.put("pageSize", 10);
		
		/***When***/
		Map<String, Object> resultMap = postService.postPagingList(map);
		List<PostVO> postList = (List<PostVO>) resultMap.get("postList");
		int pagination = (int) resultMap.get("pagination");
		
		/***Then***/
		assertEquals(10, postList.size());
		assertEquals(2, pagination);
	}
	
	
	/**
	 * Method : insertPostTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 추가 테스트
	 */
	@Test
	public void insertPostTest() {
		/***Given***/
		PostVO postVo = new PostVO(12, "sally", 2, "자유게시판1", "자유게시판1", 1);
		
		/***When***/
		int insertCnt = postService.insertPost(postVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	/**
	 * Method : postMaxCntTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 마지막 게시글 아이디 조회 테스트
	 */
	@Test
	public void postMaxCntTest() {
		/***Given***/
		
		/***When***/
		int postMaxCnt = postService.postMaxCnt();
		
		/***Then***/
		assertEquals(12, postMaxCnt);
	}
	
	
	/**
	 * Method : getPostTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 상세조회 테스트
	 */
	@Test
	public void getPostTest() {
		/***Given***/
		int postId = 1;
		
		/***When***/
		PostVO postVo = postService.getPost(postId);
		
		/***Then***/
		assertEquals("brown", postVo.getUserId());
	}
	
	
	/**
	 * Method : updatePostTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 수정 테스트
	 */
	@Test
	public void updatePostTest() {
		/***Given***/
		PostVO postVo = new PostVO(1, "brown", 1, "수정", "수정", 1);
		
		/***When***/
		int updateCnt = postService.updatePost(postVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	/**
	 * Method : answerPostTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 답글 등록 테스트
	 */
	@Test
	public void answerPostTest() {
		/***Given***/
		PostVO postVo = new PostVO(12, "sally", 1, 1, "답글", "답글", "y", 1, 2);
		
		/***When***/
		int answerPost = postService.answerPost(postVo);
		
		/***Then***/
		assertEquals(1, answerPost);
	}
	
	
	/**
	 * Method : deletePostTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 게시글 삭제 테스트
	 */
	@Test
	public void deletePostTest() {
		/***Given***/
		int postId = 1;
		
		/***When***/
		int deleteCnt = postService.deletePost(postId);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
