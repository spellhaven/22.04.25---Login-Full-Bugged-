<%@page import="com.gyojincompany.ex.MemberDto"%>
<%@page import="com.gyojincompany.ex.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%  
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id"); //login.jsp에서 넘어온 id값을 저장
		String pw = request.getParameter("pw"); //login.jsp에서 넘어온 pw값을 저장 ㅋ
		
		MemberDao dao = new MemberDao(); //MemberDao 클래스로 dao 객체 선언. ㅋ.
		int checkNum = dao.userCheck(id, pw); //로긔인성공이면 1, 회원아니면 0, 비번이 틀리면 2가 반환되게 해 놨다.
		
		if(checkNum == 0) {			
			%>
				<script type="text/javascript">
					alert("존재하지 않는 아이디입니다.");
					history.back(); //이러면 이전 페이지(로그인 페이지)로 돌아간다.
				</script>
			<%			
		} else if (checkNum == 2) {
			%>
				<script type="text/javascript">
					alert("비밀번호를 틀렸디. 다시 확인해 주세요.");
					history.back();
				</script>
			<%				
		} else {			
			MemberDto dto = dao.getMemberInfo(id); // getMemberInfo()의 리턴형을 MemberDto로 만들었으니 이렇게 받아 줘야 한다.
			// L1, L2에 page import 해서 MemberDto, MemberDao를 가져왔기 때문에 가능하다, ㅋ.	
			
			// 이렇게 dto로 가져온 값들을 세션에 좀 넣어 보자. 메인 페이지에서 "깜찍이님 안녕하세요" 이렇게 인사성 바르게 말할 수 있게.
			session.setAttribute("id", id);
			
			String pname = dto.getName();
			session.setAttribute("name", pname);
			session.setAttribute("validMem", "yes"); //이런 걸 만들어 주면 유저가 로그인 상태인가? 아닌가? 를 페이지마다 알 수 있다.
		
			response.sendRedirect("main.jsp"); // main.jsp라는 페이지로 가. "네"				
		}		
	%>
</body>
</html>