package com.gyojincompany.ex;

import java.sql.*;

public class MemberDao { // 셍성자

	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/webdb";
	static String user = "root";
	static String password = "12345";
	
	public int insertMember(MemberDto dto) {
		
		String m_id = dto.getId();
		String m_pw = dto.getPw();
		String m_name = dto.getName();
		String m_email = dto.getEmail();
		String m_address = dto.getAddress();
		
		String sql = "INSERT INTO web_members(id, pw, name, email, address) VALUES('" + m_id + "','" + m_pw + "','" + m_name + "','" + m_email + "','" + m_address + "')";
		
		int dbFlag = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			dbFlag = pstmt.executeUpdate();//sql실행->실행 성공시 1 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
		
		return dbFlag;
	}
	
	public int confirmId(String id) { //가입여부를 확인
		
		int dbFlag = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		ResultSet rs = null;
		
		String sql = "SELECT id from web_members where id = ?";
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//조건이 참이면 이미 가입된 아이디
				dbFlag = 1;				
			} else {
				dbFlag = 0;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}		
		
		return dbFlag;//가입이 되어 있으면 1이 반환, 아니면 0이 반환
	}
	
	
	// 아이디 비번을 받으면 로그인시켜 주는 함수, userCheck()를 만들어 보자.
	public int userCheck(String id, String pw) {
		
		int dbFlag = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null; //sql 실행 객체
		ResultSet rs = null;
		
		String sql = "SELECT pw from web_members where id = ?"; // 위 함수들이랑 sql문이 좀 달라졌는데 잘 봐라.
		// "아이디가 존재한다면, 비밀번호를 가져와라" 이 얘기다. 이러면 그 다음에 밑에서 세 가지 경우에 대해 신경써야 하는데...
		// 1. 비밀번호까지 일치하는지 확인하고 맞다면 => 로긔인성공. 2. 아이디는 있지만 비번이 다름 => 로긔인실패. 비번을 확인해주셈 0. 아예 아이디가 존재하지도 않는다면 => 존재하지 않는 회원입니다.
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조건이 참이면, 입력된 아이디는 DB에 이미 가입되어 있음.
				String dbPw = rs.getString("pw"); // SQL에서 "pw"라는 <<필드명>>을 찾아야 하니까, 여기엔 큰따옴표를 써야 한다.
				if(dbPw.equals(pw)) { // 여기의 pw는 우리가 입력한 argument니까 " " 쓰는 거 아니다. (이렇게 헷갈리니까, 변수 이름을 조금씩 다르게 짓는 경우가 많다함. 우리 수업에서의 u_id나 dbId 등등의 예시처럼...)
					dbFlag = 1; //로그인 성공
				} else {
					dbFlag = 2; //비번이 틀림
				}
			} else { // 조건이 거짓이면, 입력된 아이디는 DB에 이미 가입되어 있지 않음.
				dbFlag = 0;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}				
		return dbFlag; //1이면 로그인성공, 0이 반환되면 회원아님(아이디없음), 2가 반환되면 회원가입은 되어 있지만(DB에 아이디는 있지만) 비번이 틀림
	}
	
	
	
	
	// 김춘수의 <꽃>처럼 이름을 불러 주는 메소드를 만들어 보자. 로그인할 때 "깜찍이님 안녕하세요." 뭐 이렇게 말하게.
	// 근데 이왕 가져올 거, 이름만 가져오지 말고 정보를 다 가져와 보자. MemberDto(getter/setter 있는 놈)이 수고해서 가져오게 할 수 있다.
	public MemberDto getMemberInfo(String id) {		
		
		// System.out.print(id); //뭐임. 왜 print도 않됨? ㅋ
		
		MemberDto dto = null; // 이 라인 하나 없다고 에러 나냐? 자바 미친놈이
		// int는 0으로 초기화하지만, 얘는 객체니까 null로 초기화하고 시작해 보자 ㅋ.
	
		Connection conn = null;
		PreparedStatement pstmt = null; //sql 실행 객체
		ResultSet rs = null;
		
		String sql = "SELECT * from web_members where id = ?";
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			// DB에 저장되어 있던 해당 아이디의 데이터 가져오기
			if(rs.next()) {
				String dbId = rs.getString("id"); //어 왜 getString() 안에 "" 했지?? 나중에 보자.
				String dbPw = rs.getString("pw"); // 원래 sql문의 필드명은 "" 안에 쓰는 게 맞대.
				String dbName = rs.getString("name");
				String dbEmail = rs.getString("email");
				String dbAddr = rs.getString("address");
				
				dto.setId(dbId);
				dto.setPw(dbPw);
				dto.setName(dbName);
				dto.setEmail(dbEmail);
				dto.setAddress(dbAddr);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}				
		return dto;
	}
	
	
	
	// 멤버 정보 수정
	public int modifyMemberInfo(MemberDto dto) {
		// 매개변수를 String id, String pw, String name, String email, String address로 해도 상관없다.
		// 근데 어차피 그걸 담는 가방인 Dto를 만들어 놨으니까.
		
		int dbFlag = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null; //sql 실행 객체
		// ResultSet rs = null; //SELECT문이 아니니까 ResultSet이 필요도 없고
		
		String sql = "UPDATE web_members SET pw = ?, email = ?, address =? WHERE id = ?";
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPw()); // 왜 이 순서대로 받아들이는가? sql문 안의 순서다.
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			
			dbFlag = pstmt.executeUpdate(); // 수정 성공이면 1이 반환, 실패면 다른 값 반환.
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// UPDATE니까 ResultSet이 없다 ㅋ
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
		
		return dbFlag;
	}
}
