package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class BoardServiceTest extends LogicTestEnv {

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	
	/**
	* Method : boardList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 리스트 조회 테스트
	*/
	@Test
	public void boardListTest() {
		/***Given***/
	
		/***When***/
		List<BoardVO> boardList = boardService.boardList();
		
		/***Then***/
		assertEquals(2, boardList.size());
		assertEquals("공지사항", boardList.get(0).getBoardName());
	}
	
	
	/**
	* Method : boardMaxCnt
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체 수 조회 테스트
	*/
	@Test
	public void boardMaxCntTest() {
		/***Given***/
		
		/***When***/
		int boardCnt = boardService.boardMaxCnt();

		/***Then***/
		assertEquals(3, boardCnt);
	}
	
	
	/**
	* Method : addBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 새로운 게시판 추가 테스트
	*/
	@Test
	public void addBoardTest() {
		/***Given***/
		BoardVO boardVo = new BoardVO(3, "brown", "Q&A", "y");
		
		/***When***/
		int addCnt = boardService.addBoard(boardVo);
		
		/***Then***/
		assertEquals(1, addCnt);
	}
	
	
	/**
	* Method : modifyBoard
	* 작성자 : PC23
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시판 사용여부 변경 테스트
	*/
	@Test
	public void modifyBoardTest() {
		/***Given***/
		BoardVO boardVo = new BoardVO(1, "brown", "공지사항", "n");

		/***When***/
		int updateCnt = boardService.modifyBoard(boardVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	/**
	 * Method : getBoard
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 입력받은 게시판 아이디와 일치하는 게시판의 정보 테스트
	 */
	@Test
	public void getBoardTest() {
		/***Given***/
		int boardId = 2;
		
		/***When***/
		BoardVO boardVo = boardService.getBoard(boardId);
		
		/***Then***/
		assertEquals("자유게시판", boardVo.getBoardName());
	}

}
