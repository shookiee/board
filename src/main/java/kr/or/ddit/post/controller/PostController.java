package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.IAttachFileService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
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
	public String postList() {
		
		return "";
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
