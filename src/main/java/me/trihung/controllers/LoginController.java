package me.trihung.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.trihung.dao.impl.UserServiceImpl;
import me.trihung.models.UserModel;
import me.trihung.services.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	public static final String SESSION_USERNAME = "username";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
//		boolean isRememberMe = false;
//		String remember = req.getParameter("remember");
//		if ("on".equals(remember)) {
//			isRememberMe = true;
//		}
		String errorMsg = "";
		if (username == null || password == null) {
			errorMsg = "Tài khoản hoặc mật khẩu không được trống";
			req.setAttribute("color", "red");
			req.setAttribute("error", errorMsg);
			req.setAttribute("type", "Login"); 
			req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
			return;
		}
		IUserService service = new UserServiceImpl();

		UserModel user = service.login(username, password);
		if (user != null) {
			
			saveRemeber(req, user);
			
			System.out.println("User "+username+" login to web, role: "+user.getRole());
			if (user.getRole().equals("MEMBER"))
				resp.sendRedirect(req.getContextPath() + "/waiting");
			else if (user.getRole().equals("MANAGER"))
				resp.sendRedirect(req.getContextPath() + "/manager");
//			req.getRequestDispatcher("main.jsp").forward(req, resp);
		} else {
			System.out.println("login fail for user: "+username);
			errorMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("color", "red");
			req.setAttribute("error", errorMsg); 
			req.setAttribute("type", "Login"); 
			req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
			
		}

	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			if (user.getRole().equals("MEMBER"))
				resp.sendRedirect(req.getContextPath() + "/waiting");
			else if (user.getRole().equals("MANAGER"))
				resp.sendRedirect(req.getContextPath() + "/manager");
			return;
		}
//		// Check cookie
//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("username")) {
//					session = req.getSession(true);
//					resp.sendRedirect(req.getContextPath() + "/waiting");
////					req.getRequestDispatcher("main.jsp").forward(req, resp);
//					return;
//				}
//			}
//		}
		
		req.removeAttribute("error");
		req.setAttribute("type", "Login"); 
		req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
	}

	private void saveRemeber(HttpServletRequest req, UserModel usermodel) {
		HttpSession session = req.getSession();
		session.setAttribute("usermodel", usermodel);
	}
}