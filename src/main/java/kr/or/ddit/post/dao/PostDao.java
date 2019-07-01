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
		return sqlSession.selectList("post.allPostList");
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
		return sqlSession.selectList("post.boardPostList", boardId);
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
		return (Integer)sqlSession.selectOne("post.postCnt", boardId);
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
		return sqlSession.selectList("post.postPagingList", map);
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
		return sqlSession.insert("post.insertPost", postVo);
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
		return sqlSession.selectOne("post.postMaxCnt");
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
		return sqlSession.selectOne("post.getPost", postId);
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
		return sqlSession.update("post.updatePost", postVo);
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
		return sqlSession.insert("post.answerPost", postVo);
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
		return sqlSession.update("post.deletePost", postId);
	}

	
}
