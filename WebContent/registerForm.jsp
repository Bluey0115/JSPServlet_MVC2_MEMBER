<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function checkForm() {
		if (!document.newMember.userId.value) {
			alert("아이디를 입력하세요.");
			return false;
		}

		if (!document.newMember.userPassword.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		if (document.newMember.userPassword.value != document.newMember.userPassword_confirm.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
		
		if (!document.newMember.userName.value) {
			alert("이름을 입력하세요");
			return false;
		}
		
		if (!document.newMember.userEmail.value) {
			alert("이메일을 입력하세요.");
			return false;
		}	
	}
</script>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="./loginForm.jsp">로그인</a></li>
			</ul>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">회원가입</h1>
		</div>
	</div>
	<div class="container" align="center">
		<div class="col-md-4 col-md-offset-4">
			<h3 class="form-signin-heading">Please sign in</h3>
			<form action="register.do" method="post" name="newMember" onsubmit="return checkForm()">
				<div class="form-group">
					<label for="inputUserName" class="sr-only">아이디</label> 
					<input	type="text" class="form-control" placeholder="ID" name='userId' required autofocus>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">비밀번호</label> 
					<input 	type="password" class="form-control" placeholder="Password" name='userPassword' required>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">비밀번호 재입력</label> 
					<input 	type="password" class="form-control" placeholder="비밀번호 재입력" name='userPassword_confirm' required>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">이름</label> 
					<input 	type="text" class="form-control" placeholder="userName" name='userName' required>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">이메일</label> 
					<input 	type="text" class="form-control" placeholder="userEmail" name='userEmail' required>
				</div>
				<input type="submit" value="등록">
				<input type="reset" value="다시 작성하기">
			</form>
		</div>
	</div>
	<footer class="container">
		<p>&copy; Community Board</p>
	</footer>
</body>