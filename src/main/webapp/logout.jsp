<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로긔아웃</title>
</head>
<body>
	<% 
		session.invalidate(); // 세션삭제
		response.sendRedirect("main.jsp"); // 메인페이지로강제이동시키기
	%>
</body>
</html>