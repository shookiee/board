package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoTest extends LogicTestEnv{

	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	* Method : getUserTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 특정 사용자 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVO userVo = userDao.getUser(userId);
		
		/***Then***/
		assertNotNull(userVo);
		assertEquals("브라운", userVo.getName());
	}

}
