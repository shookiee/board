package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

@WebServlet("/post")
public class PostController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostController1.class);
	private IPostService postService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("PostController doGet()");
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));		
		logger.debug("boardId : {}", boardId);
		
		BoardVO boardVo = boardService.getBoard(boardId);
		request.setAttribute("boardVo", boardVo);
		logger.debug("boardVo : {}", boardVo);
		
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);
		logger.debug("page : {}", page);
		logger.debug("pageSize : {}", pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId", boardId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		
		logger.debug("map page : {}", (int)map.get("page"));
		logger.debug("map pageSize : {}", (int)map.get("pageSize"));
		
		
		
		PageVO pageVo = new PageVO(page, pageSize);
		logger.debug("pageVo : {}", pageVo);
		Map<String, Object> resultMap = postService.postPagingList(map);
		int pagination = (int) resultMap.get("pagination");
		List<PostVO> postPagingList = (List<PostVO>) resultMap.get("postPagingList");
		
		request.setAttribute("paginationSize", pagination);
		request.setAttribute("postPagingList", postPagingList);
		request.setAttribute("pageVo", pageVo);
		
		request.getRequestDispatcher("/post/post.jsp").forward(request, response);
		
		
	}


}
