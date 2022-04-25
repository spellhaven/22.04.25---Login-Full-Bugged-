<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로긔인</title>
</head>
<body>
	
	<!-- 왜 교수님은 괜찮은데 나는 loginOk 42행에서 에러가 나냐? 킹받네 -->
	<form action = "loginOk.jsp" method = "post">
	
		아이디: <input type = "text" name = "id"><br><br>
		벼밀번호: <input type = "text" name = "pw"><br><br>
		
		<input type = "submit" value = "로그인"> &nbsp; &nbsp; &nbsp;
		<input type = "button" value = "회원가입" onclick = "javascript:window.location='join.jsp'">
		<!-- 로그인을 취소하면 회원가입 페이지로 가게... -->
	
	</form>

</body>
</html>