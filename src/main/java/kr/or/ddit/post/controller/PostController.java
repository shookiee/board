package kr.or.ddit.post.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hpsf.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.IAttachFileService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/post")
@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Resource(name = "postService")
	private IPostService postService;
	
	@Resource(name = "attachFileService")
	private IAttachFileService fileService;

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	
	/**
	* Method : postList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 리스트 화면 요청
	*/
	@RequestMapping("/list")
	public String postList(int boardId, PageVO pageVo, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId", boardId);
		map.put("page", pageVo.getPage());
		map.put("pageSize", pageVo.getPageSize());
		
		Map<String, Object> resultMap = postService.postPagingList(map);
		int paginationSize = (int) resultMap.get("paginationSize");
		
		List<PostVO> postList = (List<PostVO>) resultMap.get("postList");
		
		model.addAttribute("map", map);
		model.addAttribute("postList", postList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("boardVo", boardService.getBoard(boardId));
		
		return "post/post";
	}
	
	
	/**
	* Method : readPost
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 상세 조회
	*/
	@RequestMapping("/readPost")
	public String readPost(int postId, Model model) {
		PostVO postVo = postService.getPost(postId);
		
		List<AttachFileVO> fileList = fileService.getFileList(postId);
		List<BoardVO> boardList = boardService.boardList();
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("fileList", fileList);
		model.addAttribute("board", boardList);
		
		int boardId = postVo.getBoardId();
		BoardVO boardVo = boardService.getBoard(boardId);
		
		model.addAttribute("boardVo", boardVo);
		
		List<ReplyVO> replyList = replyService.replyList(postId);
		model.addAttribute("replyList", replyList);
		
		return "post/read";
	}
	
	
	/**
	 * Method : postFormView
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @param userId
	 * @param boardId
	 * @param model
	 * @return
	 * Method 설명 : 게시글 작성 화면 요청
	 */
	@RequestMapping(path = "/postForm", method = RequestMethod.GET)
	public String postFormView(String userId, int boardId, Model model) {
		model.addAttribute("boardId", boardId);
		model.addAttribute("userId", userId);
		return "post/postForm";
	}
	
	
	/**
	 * Method : postForm
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 작성
	 */
	@RequestMapping(path = "/postForm", method = RequestMethod.POST)
	public String postForm(String userId, int boardId, String postTitle, String smarteditor,
													MultipartFile[] files, RedirectAttributes redirectAttributes) {
		int postId = postService.postMaxCnt();
		
		PostVO postVo = new PostVO(postId, userId, boardId, postTitle, smarteditor, postId);
		int insertPost = postService.insertPost(postVo);
		
		if(insertPost == 1) {
			if(files != null ) {
				int result = 0;
				List<AttachFileVO> uploadFileList = new ArrayList<AttachFileVO>();
				for (MultipartFile file : files) {
					String path = PartUtil.getUploadPath();
					String ext = PartUtil.getExt(file.getOriginalFilename());
					String fileName = UUID.randomUUID().toString();
					String fileId = path + File.separator + fileName + ext;
					AttachFileVO fileVO = new AttachFileVO(fileId, postId, path, file.getOriginalFilename());
					uploadFileList.add(fileVO);
				}
				fileService.insertFile(uploadFileList);
				
			}
			redirectAttributes.addAttribute("postId", postId);
			return "redirect:/post/readPost";
		}
		return "redirect:/post/postForm";
		
		
	}
	
	
	/**
	* Method : modifyPost
	* 작성자 : PC23
	* 변경이력 :
	* @param postId
	* @param model
	* @return
	* Method 설명 : 게시글 수정 화면 요청 
	*/
	@RequestMapping(path = "/modifyPost", method = RequestMethod.GET)
	public String modifyPostView(int postId, Model model) {
		
		model.addAttribute("postId", postId);
		
		return "post/modify";
	}
	
	
}
