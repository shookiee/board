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

@WebServlet("/answer")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IPostService postService;
	private IAttachFileService fileService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		fileService = new AttachFileService();
	}	
	
	private static final Logger logger = LoggerFactory
			.getLogger(AnswerController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int postId = Integer.parseInt(request.getParameter("postId"));

		request.setAttribute("postId", postId);
		request.getRequestDispatcher("/post/answer.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("answerController doPost()");
		
		int prefPostId = Integer.parseInt(request.getParameter("postId"));
		PostVO prefPostVo = postService.getPost(prefPostId);
		int groupId = prefPostVo.getGroupId();
		int boardId = prefPostVo.getBoardId();
		logger.debug("prefPostId : {}", prefPostId);
		int postId = postService.postMaxCnt() == 0 ? 1 : postService.postMaxCnt();
		logger.debug("postId : {}", postId);
		String userId = request.getParameter("userId");
		logger.debug("userId : {}", userId);
		String postTitle = request.getParameter("postTitle");
		logger.debug("postTitle : {}", postTitle);
		String postContent = request.getParameter("smarteditor");
		logger.debug("postContent : {}", postContent);
		logger.debug("groupId : {}", groupId);
		
		
		PostVO postVo = new PostVO();
		postVo.setGroupId(groupId);
		postVo.setPostContent(postContent);
		postVo.setPostId(postId);
		postVo.setPostTitle(postTitle);
		postVo.setBoardId(boardId);
		postVo.setPrefPostId(prefPostId);
		postVo.setUserId(userId);
		logger.debug("postVo : {}", postVo);
		
		int answerPost = postService.answerPost(postVo);
		
		if(answerPost == 1) {
			Part file1 = request.getPart("file1");
			Part file2 = request.getPart("file2");
			Part file3 = request.getPart("file3");
			Part file4 = request.getPart("file4");
			Part file5 = request.getPart("file5");
			
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
			response.sendRedirect(request.getContextPath() + "/read?postId=" + postId);
		}
	
		
		
	}
	

}
