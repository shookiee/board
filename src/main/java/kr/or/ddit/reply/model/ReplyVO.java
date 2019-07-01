package kr.or.ddit.reply.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReplyVO {
	
	private int replyId;	// 댓글 아이디
	private int postId;	// 게시글 아이디
	private String userId;	// 작성자 아이디
	private String replyContent;	// 댓글 내용
	@DateTimeFormat(pattern ="yyyy-MM-dd a hh:mm:ss")
	private Date reply_dt;	// 댓글 작성일
	private String reply_yn;	// 댓글 표시 여부(y : 표시  / y : 미표시)
	
	public ReplyVO() {

	}

	public ReplyVO(int replyId, int postId, String userId, String replyContent,
			String reply_yn) {
		super();
		this.replyId = replyId;
		this.postId = postId;
		this.userId = userId;
		this.replyContent = replyContent;
		this.reply_yn = reply_yn;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyId=" + replyId + ", postId=" + postId
				+ ", userId=" + userId + ", replyContent=" + replyContent
				+ ", reply_dt=" + reply_dt + ", reply_yn=" + reply_yn + "]";
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReply_dt() {
		return reply_dt;
	}

	public void setReply_dt(Date reply_dt) {
		this.reply_dt = reply_dt;
	}

	public String getReply_yn() {
		return reply_yn;
	}

	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}

	
}
