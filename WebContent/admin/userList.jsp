<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="../logout.do">로그아웃</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h2>회원 정보</h2>
		${message}
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>회원번호</th>
					<th>회원 아이디</th>
					<th>회원 성명</th>
					<th>회원 이메일</th>
					<th>가일 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${listOfUser}">
					<tr>
						<td>${list.userNo}</td>
						<td><a href="userDetail.act?userNo=${list.userNo}">${list.userId}</a></td>
						<td>${list.userName}</td>
						<td>${list.userEmail}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${list.reg_Date}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<c:set var="page" value="${(param.p == null)?1:param.p}"></c:set>
		<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
		<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(countUser/10), '.')}"></c:set>

		<div class="indexer margin-top align-right">
			<h3 class="hidden">현재 페이지</h3>
			<div><span class="text-orange text-strong">${(empty param.p)?1:param.p}</span> / ${lastNum} pages</div>
		</div>



		<ul class="pagination justify-content-center">
			
			<c:if test="${startNum-1<=0}">
				<li class="page-item"><a class="page-link" onclick="alert('첫 페이지 입니다.')">이전</a></li>
			</c:if>
			<c:if test="${startNum-1>0}">	
				<li class="page-item"><a class="page-link" href="?p=${startNum-1}&t=&q=">이전</a></li>
			</c:if>	

			<c:forEach var="i" begin="0" end="4">
				<c:if test="${(startNum+i) <= lastNum}">
				<li class="page-item"><a class="page-link" href="?p=${startNum+i}&t=&q=">${startNum+i}</a></li>
				</c:if>
			</c:forEach>

			<c:if test="${startNum+4<lastNum}">
				<li class="page-item"><a class="page-link" href="?p=${startNum+5}&t=&q=">다음</a></li>
			</c:if>
			<c:if test="${startNum+4>=lastNum}">
				<li class="page-item"><a class="page-link" onclick="alert('다음 페이지가 없습니다.')">다음</a></li>
			</c:if>
		</ul>



	</div>
</body>
</html>