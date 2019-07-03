package kr.or.ddit.login.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	* Method : loginView
	* 작성자 : PC23
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 사용자 로그인 화면 요청
	*/
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginView(HttpSession session) {
		if(session.getAttribute("USER_INFO") != null ) {
			return "tiles.main";					// /WEB-INF/views/main.jsp
		} else {
			return "login/login";		//	/WEB-INF/views/login/login.jsp
		}
	}
	
	
	
	/**
	* Method : loginProcess
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 로그인 요청 처리
	*/
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginProcess(String userId, String password, String rememberme,
													HttpServletResponse response, HttpSession session) {
		String encryptPassword = KISA_SHA256.encrypt(password);
		UserVO userVo = userService.getUser(userId);
		
		if(userVo != null && encryptPassword.equals(userVo.getPass())) {
			remembermeCookie(userId, rememberme, response);
			
			List<BoardVO> boardList = boardService.boardList();
			
			session.setAttribute("boardList", boardList);
			session.setAttribute("USER_INFO", userVo);
			
			return "tiles.main";
		
		} else {
			return "login/login";
		}
		
	}


	/**
	* Method : remembermeCookie
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @param rememberme
	* @param response
	* Method 설명 : rememberme 쿠키를 응답으로 생성
	*/
	private void remembermeCookie(String userId, String rememberme, HttpServletResponse response) {
		int cookieMaxAge = 0;
		
		if(rememberme != null) {
			cookieMaxAge = 60*60*24*30;
		}
		
		Cookie userIdCookie = new Cookie("userId", userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie remembermeCookie = new Cookie("rememberme", "true");
		remembermeCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(userIdCookie);
		response.addCookie(remembermeCookie);
	}
	
}
