<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<title>게시글 상세조회</title>

<script>
	$(document).ready(function() {
		$(".filelabel").on("click", function() {
			// 			alert($(this).next().val())
			$("#fileId").val($(this).next().val())
			// 			alert($("#fileId").val())
			$("#frm").submit();
		})

		$("#addReply").on("click", function() {
			$("#replyFrm").submit();
		})

		$(".delReply").on("click", function() {
			var replyId = $(this).parents("td").prevAll(".reId").html();
			$("#replyId").val(replyId);
			$("#delFrm").submit();
		})

	})
</script>
<style>
.reId {
	display: none;
}
</style>



<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${boardVo.boardName }</h2>
		<form id="frm" class="form-horizontal" role="form"
			action="${cp }/post/download" method="post">
			<input type="hidden" id="fileId" name="fileId">

			<div class="form-group">
				<label for="postTitle" class="col-sm-2 control-label">제목</label>
				<div class="col-sm-10">
					<label class="control-label">${postVo.postTitle}</label>
				</div>
			</div>
			<div class="form-group">
				<label for="userId" class="col-sm-2 control-label">작성자</label>
				<div class="col-sm-10">
					<label class="control-label">${postVo.userId}</label>
				</div>
			</div>

			<div class="form-group">
				<label for="postContent" class="col-sm-2 control-label">내용 </label>
				<div class="col-sm-10">
					<label class="control-label">${postVo.postContent}</label>
				</div>
			</div>

			<div class="form-group">
				<label for="file" class="col-sm-2 control-label">첨부파일</label>
				<div class="col-sm-10">
					<c:forEach items="${fileList }" var="file">
						<label class="control-label filelabel">${file.fileName}</label>
						<input type="hidden" class="hiddenFile" name="fileIds"
							value="${file.fileId}">
						<br>
					</c:forEach>
				</div>
			</div>


			<hr>
			<div class="form-group pull-right">
				<div class="col-sm-offset-1 col-sm-12 ">
					<a href="${cp }/post/answerPost?postId=${postVo.postId}"
						id="answerBtn" class="btn btn-default" type="submit">답글</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<c:if test="${USER_INFO.userId eq postVo.userId }">
						<a href="${cp }/post/modifyPost?postId=${postVo.postId}"
							id="updateBtn" class="btn btn-default" type="submit">수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${cp }/post/deletePost?postId=${postVo.postId}"
							id="deleteBtn" class="btn btn-default" type="submit">삭제</a>
					</c:if>
				</div>
			</div>

		</form>


		<form class="form-horizontal" role="form"
			action="${cp }/reply/deleteReply?replyId=${reply.replyId}"
			method="post">
			<div class="form-group">
				<label for="reply" class="col-sm-2 control-label">댓글 </label>
				<table>
					<c:forEach items="${replyList}" var="reply">
						<tr>
							<td class="reId">${reply.replyId }</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;${reply.replyContent }</td>
							<td>&nbsp;&nbsp;&nbsp;[${reply.userId } / <fmt:formatDate
									value="${reply.reply_dt }" pattern="yy-MM-dd hh:mm:ss" />]
							</td>
							<c:if test="${USER_INFO.userId eq reply.userId }">
								<c:if test="${reply.reply_yn != 'n' }">
									<td>&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-default delReply">삭제</button>
									</td>
								</c:if>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>


		<form id="delFrm"
			action="${cp }/reply/deleteReply?postId=${postVo.postId}">
			<input type="hidden" id="replyId" name="replyId">
		</form>

		<form id="replyFrm" class="form-horizontal" role="form"
			action="${cp }/reply/addReply?postId=${postVo.postId}"
			enctype="mutipart/form-data" method="post">
			<div class="form-group">
				<div class="col-sm-offset-2">
					<div class="col-sm-6">
						<input type="text" class=" form-control" name="replyContent"
							placeholder="댓글입력">
					</div>
					<button type="button" id="addReply" class="btn btn-default">등록</button>
				</div>
			</div>
		</form>


	</div>
</div>
</div>
