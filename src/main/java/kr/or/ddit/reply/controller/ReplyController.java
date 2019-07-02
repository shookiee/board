package kr.or.ddit.reply.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/reply")
@Controller
public class ReplyController {
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	
	/**
	 * Method : reply
	 * 작성자 : SHOOKIE
	 * 변경이력 :
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@RequestMapping(path = "/addReply", method = RequestMethod.POST)
	public String addReply(int postId, String replyContent, HttpSession session, RedirectAttributes redirectAttributes,  Model model) {
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		String userId = userVo.getUserId();
		
		ReplyVO replyVo = new ReplyVO(replyService.replyMaxCnt(), postId, userId, replyContent, "y");
		
		int insertReply = replyService.insertReply(replyVo);
		
		if(insertReply == 1) {
			List<ReplyVO> replyList = replyService.replyList(postId);
			model.addAttribute("replyList", replyList);
		}
		redirectAttributes.addAttribute("postId", postId);
		return "redirect:/post/readPost";
	}
	
	
	/**
	* Method : deleteReply
	* 작성자 : PC23
	* 변경이력 :
	* @param replyId
	* @param redirectAttributes
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@RequestMapping(path = "/deleteReply", method = RequestMethod.GET)
	public String deleteReply(int replyId, RedirectAttributes redirectAttributes) {
		ReplyVO replyVo = replyService.getReply(replyId);
		int postId = replyVo.getPostId();

		int deleteReply = replyService.deleteReply(replyId);
		
		redirectAttributes.addAttribute("postId", postId);
		return "redirect:/post/readPost";
	}
}
