package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import entity.User;

public class UserDAO {

	private static UserDAO instance = new UserDAO();

	public static UserDAO getinstance() {
		if(instance == null)
			instance = new UserDAO();
		return instance;
	}

	public UserDAO() {
		
	}

	
	// 톰캣 Context에 저장해 놓은 커넥션 풀을 이용하자.
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/bbs");

		return ds.getConnection();
	}

	
	// 회원 가입
	public void insertUser(User user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement("insert into user(userId, userPassword, userName, UserEmail) values(?,?,?,?)");
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserEmail());
			pstmt.executeUpdate();
			
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	
	}

	// 회원 인증 (로그인 처리)
	public int userCheck(String userId, String userPassword) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select userPassword from user where userId = ? ");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) 		// 찾은 컬럼의  비밀번호가 UserPassword와 같은지 비교
						x = 1;	// 인증 성공
					else x = 0;	// 비밀번호 틀림
			}else 
				x = -1;	// 해당 아이디가 없음		
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}		
		}
		return x;			
	}
	
	// 아이디 중복 확인
	
	public int confirmUserId(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select userId from user where userId = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = 1; // 같은 아이디가 존재
			else x = -1; // 같은 아이디 없음
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}	
		}
		
		return x;
	}
	
	
	// 회원 정보 수정 처리  전 입력 값 불러오기
	
	public User getUser(String userId, String userPassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from user where userId = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(2).equals(userPassword)) {
					
					user = new User();
					user.setUserId(rs.getString("userId"));
					user.setUserPassword(rs.getString("userPassword"));
					user.setUserName(rs.getString("userName"));
					user.setUserEmail(rs.getString("userEmail"));
					user.setReg_Date(rs.getDate("reg_date"));
				}
			}
					
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException e) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
				if(conn != null) try {conn.close();} catch(SQLException e) {}	
			}
			return user;	
	}
	
	
	// 회원 정보 수정 처리
	
	public int updateUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 1;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement("update user set userPassword = ?, userEmail = ?  where userId = ?");
			pstmt.setString(1, user.getUserPassword());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserId());
			pstmt.executeUpdate();
						
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}	
		}
		
		return x;
	}
	
	
	// 회원 정보 삭제
	@SuppressWarnings("resource")
	public int deleteUser(String userId, String userPassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select userPassword from user where userId = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					pstmt = conn.prepareStatement("delete from user where userId = ?");
					pstmt.setString(1, userId);
					pstmt.executeUpdate();
					x = 1;
				}else
					x = 0;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException e) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
				if(conn != null) try {conn.close();} catch(SQLException e) {}		
			}
		
		return x;				
	}
	
}
