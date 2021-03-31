package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.UserDAO;
import entity.Admin;
import entity.User;


@WebServlet("*.act")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	public void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		if (command.equals("/admin/login.act")) {	
			
			AdminDAO adminDAO = AdminDAO.getinstance();
			Admin admin = new Admin();
			
			String adminId = request.getParameter("adminId");
			String adminPassword = request.getParameter("adminPassword");
			
			int result = adminDAO.adminCheck(adminId, adminPassword);
			
			if(result == 1) {
				
				HttpSession session = request.getSession();		
				session.setAttribute("admin", admin);
				
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				
			}else if(result == 0) {
				
				request.setAttribute("error", "비밀번호가 틀렸습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				
			}else if(result == -1) {
				
				request.setAttribute("error", "아이디가 존재하지 않습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				
			}
			
		
		}
		
		else if (command.equals("/admin/userlist.act")) {
			
			AdminDAO adminDAO = AdminDAO.getinstance();
			
			ArrayList<User> listOfUser = new ArrayList<>();
			int countUser = 0;
			
			// 모든 회원 정보 가져오기
			adminDAO.getUserList(listOfUser);
			// 회원 테이블의 총 수
			countUser = adminDAO.countUser(countUser);
			
			request.setAttribute("countUser", countUser);
			request.setAttribute("listOfUser", listOfUser);
			RequestDispatcher rd = request.getRequestDispatcher("userList.jsp");
			rd.forward(request, response);
		}
		
		else if (command.equals("/admin/userDetail.act")) {
			
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			
			AdminDAO adminDAO = AdminDAO.getinstance();
			User user = new User();
			
			// 회원 상세 페이지 정보
			user = adminDAO.getUser(userNo);
			
			
			request.setAttribute("u", user);
			RequestDispatcher rd = request.getRequestDispatcher("./userDetail.jsp");
			rd.forward(request, response);
			
		}
		
		
		
		else if (command.equals("/admin/update.act")) {

			UserDAO userDAO = UserDAO.getinstance();
			User user = new User();
			
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			user.setUserNo(userNo);
			user.setUserId(request.getParameter("userId"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserName(request.getParameter("userName"));
			user.setUserEmail(request.getParameter("userEmail"));


			// 회원 정보 수정
			int result = userDAO.updateUser(user);

			if (result == 1) {

				request.setAttribute("message", "회원정보가 수정이 완료되었습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/userlist.act");
				rd.forward(request, response);

			}

			else if (result == 0) {

				request.setAttribute("message", "회원정보 수정에 실패 하셨습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/userlist.act");
				rd.forward(request, response);

			}
		}

		else if (command.equals("/admin/delete.act")) {

			UserDAO userDAO = UserDAO.getinstance();
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");


			// 회원 탈퇴
			int result = userDAO.deleteUser(userId, userPassword);

			if (result == 1) {
				
				request.setAttribute("message", "회원정보 삭제에 성공하셨습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/userlist.act");
				rd.forward(request, response);

			} else if (result == 0) {

				request.setAttribute("message", "회원정보 삭제에 실패하셨습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/userlist.act");
				rd.forward(request, response);
			}
		}
	}
}
