package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVO {
	
	private int boardId;	// 게시판 아이디
	private String userId;	// 게시판 생성자
	private String boardName;	// 게시판 이름
	private String use_yn;	// 게시판 사용여부 (y : 사용 / n : 미사용)
	private Date reg_dt;	// 게시판 생성일자
	
	public BoardVO() {
	}

	
	
	public BoardVO(int boardId, String userId, String boardName, String use_yn, Date reg_dt) {
		super();
		this.boardId = boardId;
		this.userId = userId;
		this.boardName = boardName;
		this.use_yn = use_yn;
		this.reg_dt = reg_dt;
	}



	@Override
	public String toString() {
		return "BoardVO [boardId=" + boardId + ", userId=" + userId + ", boardName=" + boardName + ", use_yn=" + use_yn
				+ ", reg_dt=" + reg_dt + "]";
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	
}
