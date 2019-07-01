package kr.or.ddit.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.model.ReplyVO;

@Service
public class ReplyService implements IReplyService {
	
	@Resource(name="replyDao")
	private IReplyDao replyDao;

	
	/**
	 * Method : replyMaxCnt
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 댓글 전체 수
	 */
	@Override
	public int replyMaxCnt() {
		return replyDao.replyMaxCnt();
	}

	
	/**
	 * Method : insertReply
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param replyVo
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@Override
	public int insertReply(ReplyVO replyVo) {
		return replyDao.insertReply(replyVo);
	}

	
	/**
	 * Method : replyList
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 해당 게시글 댓글 리스트 조회
	 */
	@Override
	public List<ReplyVO> replyList(int postId) {
		return replyDao.replyList(postId);
	}

	
	/**
	 * Method : delReplyCnt
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 댓글이 있는 게시글 삭제 시 댓글도 삭제 처리(사용여부 변경)
	 */
	@Override
	public int delReplyCnt(int postId) {
		return replyDao.delReplyCnt(postId);
	}

	
	/**
	 * Method : deleteReply
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param replyId
	 * @return
	 * Method 설명 : 댓글 삭제(사용여부 변경)
	 */
	@Override
	public int deleteReply(int replyId) {
		return replyDao.deleteReply(replyId);
	}

	
	/**
	 * Method : getReply
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param replyId
	 * @return
	 * Method 설명 : 댓글 하나의 정보
	 */
	@Override
	public ReplyVO getReply(int replyId) {
		return replyDao.getReply(replyId);
	}

}
