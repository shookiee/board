package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVO;

public interface IUserService {

	/**
	* Method : getUser
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 조회
	*/
	UserVO getUser(String userId);
}
