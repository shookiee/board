package kr.or.ddit.attachFile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachFile.model.AttachFileVO;

@Repository
public class AttachFileDao implements IAttachFileDao {

	@Resource(name="sqlSession")
	SqlSessionTemplate sqlSession;
	
	
	/**
	 * Method : insertFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param fileVo
	 * @return
	 * Method 설명 : 파일 첨부
	 */
	@Override
	public int insertFile( AttachFileVO fileVo) {
		return sqlSession.insert("file.insertFile", fileVo);
	}

	
	/**
	 * Method : getFileList
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 해당 게시글에 첨부된 파일리스트 조회
	 */
	@Override
	public List<AttachFileVO> getFileList(int postId) {
		return sqlSession.selectList("file.getFileList", postId);
	}

	
	/**
	 * Method : getFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param fileId
	 * @return
	 * Method 설명 : 해당 게시글에 첨부된 파일 조회
	 */
	@Override
	public AttachFileVO getFile(String fileId) {
		return sqlSession.selectOne("file.getFile", fileId);
	}

	
	/**
	 * Method : deleteFile
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param postId
	 * @return
	 * Method 설명 : 게시글 수정 시 해당 게시글에 첨부된 파일 삭제
	 */
	@Override
	public int deleteFile(int postId) {
		return sqlSession.delete("file.deleteFile", postId);
	}


}
