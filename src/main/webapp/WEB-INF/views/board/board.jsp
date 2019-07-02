<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시판 관리</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<style>
.blog-main {
	margin-left: 17%;
	margin-top: 3%;
}

.use_yn {
	width: 100px;
}

.table {
	text-align : center;
}

.modi{
	text-align: center;
}

.modiSel{
 	margin-left : 50px; 
	
}
</style>

<script>
$(document).ready(function(){
	if($("#updateUse_yn").val == ""){
		$("#updateUse_yn").val("y");
	}
	
	$(".updateUse_yn").change(function (){
		var use_yn =  $(this).val();
		$("#updateUse_yn").val($(this).val());
	});

	$(".modiBtn").on("click", function(){
		var boardId = $(this).parents("td").prevAll(".boardId").html();
		$("#boardId").val(boardId);
		
		var boardName = $(this).parents("td").prevAll(".updateBoardName").html();
		$("#boardName").val(boardName);
		
		$("#frm").submit();
	})
	
})
</script>
</head>

<body>
	<!-- header -->
	<%@include file= "/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp"%>
			<div class="col-sm-8 blog-main">
				<h3 class="sub-header">게시판 생성</h3>
				<form class="form-inline" id="addBoardFrm"
					action="${pageContext.request.contextPath }/board/addBoard" method="post">
					<label class="mb-2 mr-sm-2">게시판명</label> 
					<input type="text"	class="form-control mb-2 mr-sm-2" id="boardName" name="boardName" placeholder="게시판명">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<label class="mb-2 mr-sm-2">사용여부</label>
					<select class="form-control createUse_yn"	name="use_yn" required>
						<option class="use_yes" value="y">사용</option>
						<option class="use_no" value="n">미사용</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="submit" class="btn btn-primary mb-2" value="생성">
				</form>
			
			
			<br><br><br><br>
			
			<form id="frm" method="post" action="${pageContext.request.contextPath }/board/modifyBoard">
				<input type="hidden" id="boardId" name="boardId"/>
				<input type="hidden" id="updateBoardName" name="updateBoardName"/>
				<input type="hidden" id="updateUse_yn" name="updateUse_yn"/>
			</form>
			
			<h3 class="sub-header">게시판 사용 여부</h3>
			<table class="table table-striped">
				<tr>
					<th class="modi">No.</th>
					<th class="modi">게시판명</th>
					<th class="modi">사용여부</th>
					<th class="modi">수정</th>
				</tr>
			
			
				<c:forEach items="${boardList }" var="board">
					<tr class="boardTr">
						<td class="boardId">${board.boardId }</td>
						<td class="updateBoardName">${board.boardName }</td>
						
						<td class="modiSel">
							<select class="form-control updateUse_yn"	name="use_yn">
								<c:choose>
									<c:when test="${board.use_yn eq 'y' }">
										<option class="use_yes" value="y" selected="selected">사용</option>
										<option class="use_no" value="n">미사용</option>
									</c:when>
									<c:otherwise>
										<option class="use_yes" value="y">사용</option>
										<option class="use_no" value="n"  selected="selected">미사용</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
						<td>
							<button type="button" class="btn btn-primary mb-2 modiBtn">완료</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
</body>
</html>