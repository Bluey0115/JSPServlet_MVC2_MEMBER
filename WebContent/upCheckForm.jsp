<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>정보 수정 로그인</title>
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
				<a class="navbar-brand" href="./logout.do">로그아웃</a>
			</div>
		</div>
	</nav>
	<div class="container" align="center">
		<div class="col-md-4 col-md-offset-4">
			<h3 class="form-signin-heading">Please sign in</h3>
			<form class="form-signin" method="post" action="upChceckForm.do">
				<div class="form-group">
					<label for="inputUserName" class="sr-only">User ID</label> <input
						type="text" class="form-control" placeholder="ID" name="userId"
						required autofocus>
				</div>
				<p>${error}
				<div class="form-group">
					<label for="inputPassword" class="sr-only">Password</label> <input
						type="password" class="form-control" placeholder="PASSWORD"
						name="userPassword" required>
				</div>
				<button class="btn btn btn-lg btn-success btn-block" type="submit">회원정보수정</button>
			</form>
		</div>
	</div>
</body>