<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script type="text/javascript" src="js/members.js"></script>
</head>
<body>
	<form action="joinOk.jsp" method="post" name="reg_frm">
		아이디 : <input type="text" name="id" size="20"><br><br>
		비밀번호 : <input type="password" name="pw" size="20"><br><br>
		비밀번호 확인 : <input type="password" name="pw_check" size="20"><br><br>
		이름 : <input type="text" name="name" size="20"><br><br>
		이메일 : <input type="email" name="email" size="20"><br><br>
		주소 : <input type="text" name="address" size="50"><br><br>
		<input type="button" value="회원가입" onclick="infoConfirm()"> 
		<input type="reset" value="취소" onclick="javascript:window.location='login.jsp'">
		<!-- 회원가입을 취소하면 로그인 페이지로 가게... -->
	
	</form>
</body>
</html>