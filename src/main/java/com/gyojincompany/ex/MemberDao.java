package com.gyojincompany.ex;

import java.sql.*;

public class MemberDao {

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
}
