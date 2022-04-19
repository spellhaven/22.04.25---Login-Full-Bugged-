<%@page import="com.gyojincompany.ex.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dto" class="com.gyojincompany.ex.MemberDto"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 성공</title>
</head>
<body>
	<%
	MemberDao dao = new MemberDao();

	int dbResult = dao.insertMember(dto);//DB저장 성공이면 1, 아니면 0으로 반환
	
	if (dbResult == 1) {
		out.println("회원가입성공! 가입을 축하드립니다.")	;
	} else {
		out.println("회원가입실패! 다시 확인해 주세요.")	;
	}	

%>
</body>
</html>