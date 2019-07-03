package kr.or.ddit.attachFile.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.attachFile.dao.IAttachFileDao;
import kr.or.ddit.attachFile.model.AttachFileVO;

@Service
public class AttachFileService implements IAttachFileService {

	@Resource(name="attachFileDao")
	private IAttachFileDao fileDao;
	
	
	/**
	 * Method : insertFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param uploadFileList
	 * @return
	 * Method 설명 : 파일 첨부
	 */
	@Override
	public int insertFile(List<AttachFileVO> uploadFileList) {
		
		int insertCntSum = 0;
		
		for(AttachFileVO fileVo : uploadFileList){
			int insertCnt = fileDao.insertFile(fileVo);
			
			insertCntSum += insertCnt;
		}
		return insertCntSum;
	}

	
	/**
	 * Method : getFileList
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 해당 게시글에 첨부된 파일 리스트
	 */
	@Override
	public List<AttachFileVO> getFileList(int postId) {
		return fileDao.getFileList(postId);
	}

	
	/**
	 * Method : getFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param fileId
	 * @return
	 * Method 설명 : 해당게시글에 첨부된 파일 조회
	 */
	@Override
	public AttachFileVO getFile(String fileId) {
		return fileDao.getFile(fileId);
	}


	/**
	* Method : delUpdateFiles
	* 작성자 : PC23
	* 변경이력 :
	* @param delFileIds
	* Method 설명 : 게시글 수정 시 파일이 수정되었으면 기존의 파일 데이터 삭제
	*/
	@Override
	public void delUpdateFiles(String[] delFileIds) {
		int delCntSum = 0;
		
		for(String delFileId : delFileIds) {
			int delCnt = fileDao.deleteFile(delFileId);
			
			if(delCnt != 1) {
				break;
			}
			delCntSum += delCnt;
		}
		
	}

	
	

}
