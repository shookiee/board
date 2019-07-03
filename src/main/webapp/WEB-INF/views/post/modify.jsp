<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<title>게시글 수정</title>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">게시글 수정</h2>

		<form id="frm2" class="form-horizontal" role="form"
			action="${cp }/post/modifyPost" method="post"
			enctype="multipart/form-data">

			<div class="form-group">
				<div class="col-sm-10">
					<%@include file="/SE2/modifyIndex.jsp"%>
				</div>
			</div>
		</form>
	</div>
</div>
