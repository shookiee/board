package kr.or.ddit.post.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class PostDaoTest extends LogicTestEnv {

	@Resource(name = "postDao")
	private IPostDao postDao;
	
	
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
		List<PostVO> postList = postDao.allPostList();
		
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
		List<PostVO> boardPostList =postDao.boardPostList(boardId);
		
		/***Then***/
		assertEquals(11, boardPostList.size());
	}
	
	
	/**
	 * Method : postCntTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 특정 게시판 게시글 갯수 테스트
	 */
	@Test
	public void postCntTest() {
		/***Given***/
		int boardId = 1;
		
		/***When***/
		int postCnt = postDao.postCnt(boardId);
		
		/***Then***/
		assertEquals(11, postCnt);
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
		List<PostVO> postList = postDao.postPagingList(map);
		
		/***Then***/
		assertEquals(10, postList.size());
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
		int insertCnt = postDao.insertPost(postVo);
		
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
		int postMaxCnt = postDao.postMaxCnt();
		
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
		PostVO postVo = postDao.getPost(postId);
		
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
		int updateCnt = postDao.updatePost(postVo);
		
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
		int answerPost = postDao.answerPost(postVo);
		
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
		int deleteCnt = postDao.deletePost(postId);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
}