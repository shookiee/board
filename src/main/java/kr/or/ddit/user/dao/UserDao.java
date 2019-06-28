package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDao implements IUserDao{
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	* Method : getUser
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

}
