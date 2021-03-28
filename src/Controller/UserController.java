package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

@WebServlet("*.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	public void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		// 회원가입 
		if(command.equals("/register.do")) {
			UserDAO userDAO = UserDAO.getinstance();
			User user = new User();
			
			user.setUserId(request.getParameter("userId"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserName(request.getParameter("userName"));
			user.setUserEmail(request.getParameter("userEmail"));
				
			userDAO.insertUser(user);
			
			RequestDispatcher rd = request.getRequestDispatcher("/loginForm.jsp");
			rd.forward(request, response);
		}
		
		
		// 로그인 설정
		else if(command.equals("/login.do")) {
			
			UserDAO userDAO = UserDAO.getinstance();
			User user = new User();
			
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");
			
			
			int result = userDAO.userCheck(userId, userPassword);
			
			if(result == 1) {
			
			// 세션 등록	
			HttpSession session  = request.getSession();
			session.setAttribute("userId", userId);
				
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			}
			else if(result == 0) {
	
				request.setAttribute("error","비밀번호가 틀렸습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/loginForm.jsp");
				rd.forward(request, response);
			}
			else if(result == -1)
				
				request.setAttribute("error", "아이디가 존재하지 않습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/loginForm.jsp");
				rd.forward(request, response);
		}
		
		//로그 아웃
		else if(command.equals("/logout.do")) {
			
			HttpSession session  = request.getSession();
			session.invalidate();
			
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
		}
		
		//정보 수정 확인 페이지
		else if(command.equals("/upChceckForm.do")) {
			
			UserDAO userDAO = UserDAO.getinstance();
			User user = new User();
			
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");
			
			int result = userDAO.userCheck(userId, userPassword);
			
			if(result == 1) {
				
			user = userDAO.getUser(userId,userPassword);
			
			request.setAttribute("u", user);
			
			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);
			}
			else if(result == 0) {
	
				request.setAttribute("error","비밀번호가 틀렸습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/upCheckForm.jsp");
				rd.forward(request, response);
			}
			else if(result == -1) {
				
				request.setAttribute("error", "아이디가 존재하지 않습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/upCheckForm.jsp");
				rd.forward(request, response);
			}
		}
		
		
		// 회원 정보 수정 처리
		else if(command.equals("/update.do")) {
			
			UserDAO userDAO = UserDAO.getinstance();
			User user = new User();
			
			user.setUserId(request.getParameter("userId"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserName(request.getParameter("userName"));
			user.setUserEmail(request.getParameter("userEmail"));
			
			System.out.println(user);
			
			int result = userDAO.updateUser(user);
			
			
			if(result == 1) {
				
			request.setAttribute("message","회원정보가 수정이 완료되었습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
			}
			
			else if(result == 0) {
				
			request.setAttribute("message", "회원정보 수정에 실패 하셨습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
			}			
		}
		
		// 회원 탈퇴
		else if(command.equals("/delete.do")) {
		
			UserDAO userDAO = UserDAO.getinstance();
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");
			
			System.out.println(userId + userPassword);
			
			int result = userDAO.deleteUser(userId,userPassword);
			
			if(result == 1) {
			
			HttpSession session  = request.getSession();
			
			System.out.println(session);
			
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
			}
			else if(result == 0) {
			
			request.setAttribute("error", "비밀번호가 맞지 않습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/upCheckForm.jsp");
			rd.forward(request, response);
			}
		}
	}
}
