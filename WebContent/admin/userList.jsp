<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
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
  <h2>회원 관리</h2>          
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
        <td>${list.userId}</td>
        <td>${list.userName}</td>
        <td>${list.userEmail}</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.reg_Date}"/></td> 
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>



</body>
</html>