<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>게시글 리스트</title>

<script>
	$(document).ready(function() {
		// 사용자 tr 태그 이벤트 등록
		$(".postTr").on("click", function() {

			var postId = $(this).find(".postId").text();
			$("#postId").val(postId);
			var post_yn = $(this).find(".post_yns").val()
			$("#post_yn").val(post_yn);
			//#frm을 이용하여 submit();
			if ($("#post_yn").val() == "y") {
				$("#frm").submit();
			}
		})
	})
</script>


<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${boardVo.boardName }</h2>

		<form id="frm" action="${cp }/post/readPost">
			<input type="hidden" id="postId" name="postId"> <input
				type="hidden" id="userId" value="${USER_INFO.userId }"> <input
				type="hidden" id="post_yn" value="${post.post_yn}">
		</form>


		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일시</th>
				</tr>

				<c:forEach items="${postList}" var="post">
					<tr class="postTr">
						<form>
							<input type="hidden" class="post_yns" value="${post.post_yn }">
						</form>
						<td class="postId">${post.postId }</td>
						<c:choose>
							<c:when test="${post.lv > 1 }">
								<td><c:forEach begin="1" end="${post.lv}">
														&nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach> └▶ ${post.postTitle}</td>
							</c:when>
							<c:otherwise>
								<td>${post.postTitle}</td>
							</c:otherwise>
						</c:choose>
						<td>${post.userId }</td>
						<td><fmt:formatDate value="${post.post_dt }"
								pattern="yyyy-MM-dd a hh:mm:ss" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<c:if test="${USER_INFO != null }">
			<a
				href="${cp }/post/postForm?boardId=${boardVo.boardId}&userId=${USER_INFO.userId}"
				class="btn btn-default pull-right">게시글 작성</a>
		</c:if>
		<div class="text-center">
			<ul class="pagination">

				<%-- 	<%
									// 내가 현재 몇번째 페이지에 있는가?
									PageVO pageVo = (PageVO) request.getAttribute("pageVO");
									int paginationSize = (Integer) request
											.getAttribute("paginationSize");
								%> --%>

				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled"><span>≪</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp }/post/list?boardId=${boardVo.boardId }&page=1&pageSize=${pageVo.pageSize}">≪</a>
						</li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled"><span>＜</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp }/post/list?boardId=${boardVo.boardId }&page=${pageVo.page -1}&pageSize=${pageVo.pageSize}">＜</a>
						</li>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="1" end="${paginationSize }" step="1" var="i">
					<li><c:choose>
							<c:when test="${pageVo.page  == i}">
								<li class="active"><span>${i }</span></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${cp }/post/list?boardId=${boardVo.boardId }&page=${i}&pageSize=${pageVo.pageSize}">${i }</a></li>
							</c:otherwise>
						</c:choose></li>
				</c:forEach>

				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled"><span>＞</span></li>
					</c:when>

					<c:otherwise>
						<li><a
							href="${cp }/post/list?boardId=${boardVo.boardId }&page=${pageVo.page + 1}&pageSize=${pageVo.pageSize}">＞</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled"><span>≫</span></li>
					</c:when>

					<c:otherwise>
						<li><a
							href="${cp }/post/list?boardId=${boardVo.boardId }&page=${paginationSize}&pageSize=${pageVo.pageSize}">≫</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>

