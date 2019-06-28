package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {
	
	List<BoardVO> boardList();
	
	int boardCnt();
	
	int addBoard();
	
	int modifyBoard(BoardVO boardVo);
	
	BoardVO getBoard(int boardId);
}
