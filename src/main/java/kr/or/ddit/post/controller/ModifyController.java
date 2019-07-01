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

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.AttachFileService;
import kr.or.ddit.attachFile.service.IAttachFileService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/modify")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(ModifyController.class);

	private IPostService postService;
	private IAttachFileService fileService;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		fileService = new AttachFileService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ModifyController doGet()");
		String userId = request.getParameter("userId");
		int postId = Integer.parseInt(request.getParameter("postId"));

		request.setAttribute("postId", postId);
		request.getRequestDispatcher("/post/modify.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int postId = Integer.parseInt(request.getParameter("postId"));
		String userId = request.getParameter("userId");
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("smarteditor");

		PostVO postVo = new PostVO();
		postVo.setPostId(postId);
		postVo.setPostTitle(postTitle);
		postVo.setPostContent(postContent);

		int updateCnt = postService.updatePost(postVo);

		if (updateCnt == 1) {

			List<AttachFileVO> exFileList = fileService.getFileList(postId);
			if (exFileList != null) {
				int deleteCnt = fileService.deleteFile(postId);

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
				List<AttachFileVO> uploadFileList = PartUtil.fileList(postId,
						fileList);
				if (uploadFileList != null) {
					result = fileService.insertFile(uploadFileList);

					if (result >= 1) {
						response.sendRedirect(request.getContextPath() + "/read?postId=" + postId);
					}
				}

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/read?postId=" + postId);
		}
	}

}
