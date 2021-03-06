<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
	<%!String greeting = "MVC2 회원관리 구현해보기";%>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="../admin/index.jsp">Home</a>
			</div>
			<c:choose>
			<c:when test="${empty admin}">
			<ul class="nav navbar-nav">
				<li class="active"><a href="./login.jsp">로그인</a></li>
			</ul>
			</c:when>
			<c:otherwise>
			<ul class="nav navbar-nav">
				<li class="active"><a href="../logout.do">로그아웃</a></li>
			</ul>
			<ul class="nav navbar-nav">
				<li class="active"><a href="userlist.act">회원 정보</a></li>
			</ul>
			</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				관리자 페이지 입니다.
			</h1>
		</div>
	</div>	
	<div class="container">
		<div class="text-center">
		</div>
		<hr>
	</div>	
</body>
</html>