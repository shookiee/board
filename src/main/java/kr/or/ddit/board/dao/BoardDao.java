package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

@Repository
public class BoardDao implements IBoardDao {

	@Resource(name="sqlSession")
	SqlSessionTemplate sqlSession;
	
	
	/**
	* Method : boardList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 리스트 조회
	*/
	@Override
	public List<BoardVO> boardList() {
		List<BoardVO> boardList = sqlSession.selectList("board.boardList");
		return boardList;
	}

	
	/**
	* Method : boardMaxCnt
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체 수 조회
	*/
	@Override
	public int boardMaxCnt() {
		int boardCnt = (Integer)sqlSession.selectOne("board.boardMaxCnt");
		
		return boardCnt;
	}

	
	/**
	* Method : addBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 새로운 게시판 추가
	*/
	@Override
	public int addBoard(BoardVO boardVo) {
		int addBoard = sqlSession.insert("board.addBoard", boardVo);
		
		return addBoard;
	}

	
	/**
	* Method : modifyBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시판 사용여부 변경
	*/
	@Override
	public int modifyBoard(BoardVO boardVo) {
		int updateCnt = sqlSession.update("board.modifyBoard", boardVo);
		
		return updateCnt;
	}


	/**
	 * Method : getBoard
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 입력받은 게시판 아이디와 일치하는 게시판의 정보
	 */
	@Override
	public BoardVO getBoard(int boardId) {
		BoardVO boardVo = sqlSession.selectOne("board.getBoard", boardId);
		
		return boardVo;
	}

	
}
