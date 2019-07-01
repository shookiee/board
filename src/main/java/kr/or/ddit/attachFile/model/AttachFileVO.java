package kr.or.ddit.attachFile.model;

public class AttachFileVO {

	private String fileId;	// 파일 아이디
	private int postId;	// 게시글 아이디
	private String filePath;	// 파일 경로
	private String fileName;	// 파일명
	
	public AttachFileVO() {

	}

	public AttachFileVO(String fileId, int postId, String filePath,
			String fileName) {
		super();
		this.fileId = fileId;
		this.postId = postId;
		this.filePath = filePath;
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "AttachFileVO [fileId=" + fileId + ", postId=" + postId
				+ ", filePath=" + filePath + ", fileName=" + fileName + "]";
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
