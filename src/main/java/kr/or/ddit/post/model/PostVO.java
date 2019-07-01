package kr.or.ddit.post.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PostVO {

	private int postId;		// 게시글 아이디
	private String userId;		// 작성자 아이디
	private int boardId;		// 게시판 아이디
	private int prefPostId;		// 부모글 아이디
	private String postTitle;	// 게시글 제목
	private String postContent;	// 게시글 내용
	@DateTimeFormat(pattern ="yyyy-MM-dd a hh:mm:ss")
	private Date post_dt;		// 작성일
	private String post_yn;		// 게시글 보기 여부(y : 보이기  / n : 보이지 않기)
	private int groupId;	// 그룹 아이디
	private int lv;
	
	public PostVO() {

	}

	public PostVO(int postId, String userId, int boardId, int prefPostId,
			String postTitle, String postContent, String post_yn, int groupId,
			int lv) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.boardId = boardId;
		this.prefPostId = prefPostId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.post_yn = post_yn;
		this.groupId = groupId;
		this.lv = lv;
	}

	public PostVO(int postId, String userId, int boardId, String postTitle,
			String postContent, int groupId) {
		this.postId = postId;
		this.userId = userId;
		this.boardId = boardId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", userId=" + userId + ", boardId="
				+ boardId + ", prefPostId=" + prefPostId + ", postTitle="
				+ postTitle + ", postContent=" + postContent + ", post_dt="
				+ post_dt + ", post_yn=" + post_yn + ", groupId=" + groupId
				+ ", lv=" + lv + "]";
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getPrefPostId() {
		return prefPostId;
	}

	public void setPrefPostId(int prefPostId) {
		this.prefPostId = prefPostId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPost_dt() {
		return post_dt;
	}

	public void setPost_dt(Date post_dt) {
		this.post_dt = post_dt;
	}

	public String getPost_yn() {
		return post_yn;
	}

	public void setPost_yn(String post_yn) {
		this.post_yn = post_yn;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	
}
