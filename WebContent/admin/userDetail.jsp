<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function checkForm() {
	if (!document.updateUser.userId.value) {
		alert("아이디를 입력하세요.");
		return false;
	}
	
	if (!document.updateUser.userName.value) {
		alert("이름을 입력하세요");
		return false;
	}
	
	if (!document.updateUser.userEmail.value) {
		alert("이메일을 입력하세요.");
		return false;
	}	
}
</script>	
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="./logout.do">로그아웃</a></li>
			</ul>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">회원정보 수정</h1>
		</div>
	</div>
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<form method="post" name="updateUser" onsubmit="return checkForm()">
				<div class="form-group">
					<input type="hidden" class="form-control"  name='userNo' value="${u.userNo}" readonly>
				</div>
				<div class="form-group">
					<label for="inputName">아이디 : </label>
					<input type="text" class="form-control"  name='userId' value="${u.userId}" readonly>
				</div>
				<div class="form-group">
					<label for="inputName">비밀번호 : </label> 
					<input 	type="text" class="form-control"  name='userPassword' value="${u.userPassword}">
				</div>
				<div class="form-group">
					<label for="inputName">이름 : </label> 
					<input 	type="text" class="form-control"  name='userName' value="${u.userName}" readonly>
				</div>
				<div class="form-group">
					<label for="inputPassword">이메일 : </label> 
					<input 	type="text" class="form-control" name='userEmail' value="${u.userEmail}" required>
				</div>
				<div class="form-group">
					<label for="inputPassword">가입 날짜 : </label> 
					<input 	type="text" class="form-control" name='reg_Date' value="${u.reg_Date}" readonly>
				</div>
				<input type="submit" value="수정" onclick="javascript_:document.updateUser.action='./update.act';">
				<input type="submit" value="탈퇴" onclick="javascript_:document.updateUser.action='./delete.act';">
			</form>
		</div>
	</div>
	<footer class="container">
		<p>&copy; Community Board</p>
	</footer>
</body>