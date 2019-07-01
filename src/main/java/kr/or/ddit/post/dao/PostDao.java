package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;

@Repository
public class PostDao implements IPostDao {

	@Resource(name="sqlSession")
	SqlSessionTemplate sqlSession;

	
	/**
	* Method : postList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 전체 게시글 리스트 조회
	*/
	@Override
	public List<PostVO> allPostList() {
		List<PostVO> allPostList = sqlSession.selectList("post.allPostList");
		
		return allPostList;
	}
	
	
	/**
	* Method : boardPostList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 특정 게시판 게시글 조회
	*/
	@Override
	public List<PostVO> boardPostList(int boardId) {
		List<PostVO> boardPostList = sqlSession.selectList("post.boardPostList", boardId);
		
		return boardPostList;
	}

	
	/**
	* Method : postCnt
	* 작성자 : PC23
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 특정 게시판 게시글 갯수
	*/
	@Override
	public int postCnt(int boardId) {
		int postCnt = (Integer)sqlSession.selectOne("post.postCnt", boardId);
		
		return postCnt;
	}


	/**
	 * Method : postPagingList
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param map
	 * @return
	 * Method 설명 : 게시글 페이징 리스트
	 */
	@Override
	public List<PostVO> postPagingList(Map<String, Object> map) {
		List<PostVO> postPagingList = sqlSession.selectList("post.postPagingList", map);
		
		return postPagingList;
	}


	/**
	 * Method : insertPost
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 추가
	 */
	@Override
	public int insertPost(PostVO postVo) {
		int insertCnt =sqlSession.insert("post.insertPost", postVo);
		
		return insertCnt;
	}


	/**
	 * Method : postMaxCnt
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 마지막 게시글 아이디 조회
	 */
	@Override
	public int postMaxCnt() {
		int postMaxCnt = sqlSession.selectOne("post.postMaxCnt");
		return postMaxCnt;
	}


	/**
	 * Method : getPost
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 게시글 상세조회
	 */
	@Override
	public PostVO getPost(int postId) {
		PostVO postVo = sqlSession.selectOne("post.getPost", postId);
		return postVo;
	}


	/**
	 * Method : updatePost
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePost(PostVO postVo) {
		int updateCnt = sqlSession.update("post.updatePost", postVo);

		return updateCnt;
	}


	/**
	 * Method : answerPost
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 답글 등록
	 */
	@Override
	public int answerPost(PostVO postVo) {
		int answerPost = sqlSession.insert("post.answerPost", postVo);

		return answerPost;
	}


	/**
	 * Method : deletePost
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 게시글 삭제(사용여부 미사용으로 변경)
	 */
	@Override
	public int deletePost(int postId) {
		int deleteCnt = sqlSession.update("post.deletePost", postId);
		
		return deleteCnt;
	}

	
}
