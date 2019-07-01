package kr.or.ddit.attachFile.service;

import java.util.List;

import kr.or.ddit.attachFile.model.AttachFileVO;

public interface IAttachFileService {
	
	/**
	 * Method : insertFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param uploadFileList
	 * @return
	 * Method 설명 : 파일 첨부
	 */
	int insertFile(List<AttachFileVO> uploadFileList);

	
	/**
	 * Method : getFileList
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 해당 게시글에 첨부된 파일 리스트 조회
	 */
	List<AttachFileVO> getFileList(int postId);

	
	/**
	 * Method : getFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param fileId
	 * @return
	 * Method 설명 : 해당게시글에 첨부된 파일 조회
	 */
	AttachFileVO getFile(String fileId);

	
	/**
	 * Method : deleteFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 게시글 수정 시 해당 게시글에 첨부된 파일 삭제
	 */
	int deleteFile(int postId);
}
