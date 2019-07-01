package kr.or.ddit.reply.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ReplyDaoTest extends LogicTestEnv {

	@Resource(name = "replyDao")
	private IReplyDao replyDao;
	
	
	/**
	 * Method : replyMaxCntTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 해당 게시글의 댓글 전체 수 테스트
	 */
	@Test
	public void replyMaxCntTest() {
		/***Given***/
		int postId = 1;
		
		/***When***/
		int replyCnt = replyDao.replyMaxCnt();
		
		/***Then***/
		assertEquals(6, replyCnt);
	}
	
	
	/**
	 * Method : insertReply
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param replyVo
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVO replyVo = new ReplyVO(6, 1, "brown", "댓글", "y");
		
		/***When***/
		int insertReply = replyDao.insertReply(replyVo);
		
		/***Then***/
		assertEquals(1, insertReply);
	}
	
	
	/**
	 * Method : replyListTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 해당 게시글 댓글 리스트 조회
	 */
	@Test
	public void replyListTest() {
		/***Given***/
		int postId = 1;
		
		/***When***/
		List<ReplyVO> replyList = replyDao.replyList(postId);
		
		/***Then***/
		assertEquals(3, replyList.size());
	}
	
	
	/**
	 * Method : delReplyCntTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 댓글 있는 게시글 삭제 시 댓글도 삭제처리
	 */
	@Test
	public void delReplyCntTest() {
		/***Given***/
		int postId = 1;
		
		/***When***/
		int delCnt = replyDao.delReplyCnt(postId);
		
		/***Then***/
		assertEquals(3, delCnt);
	}
	
	
	/**
	 * Method : deleteReplyTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 댓글 삭제 테스트
	 */
	@Test
	public void deleteReplyTest() {
		/***Given***/
		int replyId = 1;
		
		/***When***/
		int deleteCnt = replyDao.deleteReply(replyId);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
	/**
	 * Method : getReplyTest
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * Method 설명 : 댓글 하나의 정보 테스트
	 */
	@Test
	public void getReplyTest() {
		/***Given***/
		int replyId = 1;
		
		/***When***/
		ReplyVO replyVo = replyDao.getReply(replyId);
		
		/***Then***/
		assertEquals("sally", replyVo.getUserId());
	}

}
