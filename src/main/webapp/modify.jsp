<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.gyojincompany.ex.MemberDao" %>    
<%@ page import ="com.gyojincompany.ex.MemberDto" %>      
    
<% 
	MemberDao dao = new MemberDao();
	String id = (String)session.getAttribute("id");

	MemberDto dto = dao.getMemberInfo(id);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정 ㅋ</title>
</head>
<body>
	<form action = "modifyOk.jsp" method = "post">
		아이디: <%= dto.getId() %> <br><br> <!-- 회원정보수정 페이지더라도 아이디는 고치면 안 된다. 기본키(PK)잖아. -->
		비밀번호: <input type = "password" name = "pw"><br><br> <!-- 이거 password "" 감싸야 되나? 안 감싸야 하나? -->
		
		이름: <%= dto.getName() %><br><br>
		
		이메일: <input type = "text" name = "email" value = "<%= dto.getEmail() %>"><br><br>
		주소: <input type = "text" name = "address" size = "50" value = "<%= dto.getAddress() %>"><br><br>
		
		<input type = "submit" value = "수정완료"> &nbsp; &nbsp; &nbsp;		
		<input type = "button" value = "취소" onclick = "javascript:window.location='main.jsp'">	
	</form>

</body>
</html>