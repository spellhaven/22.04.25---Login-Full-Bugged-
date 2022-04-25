<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% // 와 JSP 포워딩을 맨 위에서 해 줘야 된다니 진짜 데박이다
	if(session.getAttribute("vaildMem") == null) { //참이면 로그인이 안 된 유저가 온 거임 ㅋ 로그인 페이지로 보내버리자 ㅋ
%>		
		<jsp:forward page = "login.jsp">
<% 
	}
		String id = (String) session.getAttribute("id"); // 세션에서 가져온 아이디는 객체형이다. String으로 형변환.
		String name = (String) session.getAttribute("name"); // 세션에서 이름 가져오기
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>

	<h2><%= name %>님 환영합니다.</h2>
	<h2><%= id %>로그인 완료</h2>
	
	<form action = "logout.jsp" method = "post">
		<input type = "submit" value = "로그아웃">
		<input type = "button" value = "정보수정" onclick = "javascript:window.location='modify.jsp'">
	</form>
	
</body>
</html>