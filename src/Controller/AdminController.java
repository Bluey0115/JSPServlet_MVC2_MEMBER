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
			
			adminDAO.getUserList(listOfUser);
			
			
			request.setAttribute("listOfUser", listOfUser);
			RequestDispatcher rd = request.getRequestDispatcher("userList.jsp");
			rd.forward(request, response);
		}
			
	}

}
