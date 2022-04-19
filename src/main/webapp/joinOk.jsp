<%@page import="com.gyojincompany.ex.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dto" class=com.gyojincompany.ex.MemberDto></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%
	MemberDao dao = new MemberDao();

	dao.insertMember(dto);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 성공</title>
</head>
<body>
	
</body>
</html>