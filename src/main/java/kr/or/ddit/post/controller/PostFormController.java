package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.AttachFileService;
import kr.or.ddit.attachFile.service.IAttachFileService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.PartUtil;

@WebServlet("/postForm")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class PostFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IAttachFileService fileService;
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		fileService = new AttachFileService();
		postService = new PostService();
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostFormController.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		request.setAttribute("boardId", boardId);
		request.getRequestDispatcher("/post/postForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int postId = postService.postMaxCnt();
		String userId = request.getParameter("userId");
		String boardIdStr = request.getParameter("boardId");
		int boardId = Integer.parseInt(boardIdStr);
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("smarteditor");
		logger.debug("boardId : {}", boardId);
		logger.debug("userId : {}", userId);
		logger.debug("postTitle : {}", postTitle);
		logger.debug("postContent : {}", postContent);
		
		PostVO postVo = new PostVO(postId, userId, boardId, postTitle, postContent, postId);
		int insertPost = postService.insertPost(postVo);
		
		if(insertPost == 1) {
			Part file1 = request.getPart("file1");
			Part file2 = request.getPart("file2");
			Part file3 = request.getPart("file3");
			Part file4 = request.getPart("file4");
			Part file5 = request.getPart("file5");
//			logger.debug("file1Size : {}", file1.getSize());
//			logger.debug("file2Size : {}", file2.getSize());
//			logger.debug("file3Size : {}", file3.getSize());
//			logger.debug("file4Size : {}", file4.getSize());
//			logger.debug("file5Size : {}", file5.getSize());
			
			List<Part> fileList = new ArrayList<Part>();
			fileList.add(file1);
			fileList.add(file2);
			fileList.add(file3);
			fileList.add(file4);
			fileList.add(file5);
			
			int result = 0;
			List<AttachFileVO> uploadFileList =  PartUtil.fileList(postId, fileList);
			if (uploadFileList != null) {
				result = fileService.insertFile(uploadFileList);
				
				if (result >= 1) {
					response.sendRedirect(request.getContextPath() + "/read?postId=" + postId);
				}
			}
			
		} else {
			response.sendRedirect(request.getContextPath() + "/post");
		}
		
		
		
	}

}
