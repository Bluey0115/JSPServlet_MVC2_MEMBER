package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import entity.User;

public class AdminDAO {
	
	private static AdminDAO instance = new AdminDAO();
	
	private ArrayList<User> listOfUser = new ArrayList<User>();
	
	
	public static AdminDAO getinstance() {
		if (instance == null)
			instance = new AdminDAO();
		return instance;
	}

	public AdminDAO() {

	}
	
	
	// 커넥션 풀
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/bbs");

		return ds.getConnection();
	}
	
	
	// 관리자(로그인 처리)
		public int adminCheck(String adminId, String adminPassword) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = -1;

			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select adminPassword from admin where adminId = ? ");
				pstmt.setString(1, adminId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getString(1).equals(adminPassword)) // 인덱스 번호로 찾은 컬럼의 비밀번호가 adminPassword와 같은지 비교
						x = 1; // 인증 성공
					else
						x = 0; // 비밀번호 틀림
				} else
					x = -1; // 해당 아이디가 없음

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)
					try {rs.close();} catch (SQLException e) {}
				if (pstmt != null)
					try {pstmt.close();} catch (SQLException e) {}
				if (conn != null)
					try {conn.close();} catch (SQLException e) {}
			}
			return x;
		}
		
		// 회원 리스트 불러오기
		public ArrayList<User> getUserList(ArrayList<User> listOfUser){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from user order by userNo asc ");
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					User user = new User();
					
					user.setUserNo(rs.getInt("userNo"));
					user.setUserId(rs.getString("userId"));
					user.setUserName(rs.getString("userName"));
					user.setUserEmail(rs.getString("userEmail"));
					user.setReg_Date(rs.getDate("reg_Date"));
					
					listOfUser.add(user);
					
				}	
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null)
					try {rs.close();} catch (SQLException e) {}
				if (pstmt != null)
					try {pstmt.close();} catch (SQLException e) {}
				if (conn != null)
					try {conn.close();} catch (SQLException e) {}
			}
			return listOfUser;			
		}
		
		
		
		
		
		
		
}
