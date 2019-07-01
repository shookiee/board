package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {

	/**
	* Method : boardList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 리스트 조회
	*/
	List<BoardVO> boardList();
	
	
	/**
	* Method : boardMaxCnt
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체 수 조회
	*/
	int boardMaxCnt();
	
	
	/**
	* Method : addBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 새로운 게시판 추가
	*/
	int addBoard(BoardVO boardVo);
	
	
	/**
	* Method : modifyBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 사용여부 변경
	*/
	int modifyBoard(BoardVO boardVo);


	/**
	 * Method : getBoard
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 입력받은 게시판 아이디와 일치하는 게시판의 정보
	 */
	BoardVO getBoard(int boardId);
}
