package me.trihung.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.trihung.dao.UserServiceImpl;
import me.trihung.services.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	public static final String REGISTER = "register.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
//		if (session != null && session.getAttribute("username") != null) {
//			resp.sendRedirect(req.getContextPath() + "/admin");
//			return;
//		}
		// Check cookie
//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("username")) {
//					session = req.getSession(true);
//					session.setAttribute("username", cookie.getValue());
//					resp.sendRedirect(req.getContextPath() + "/admin");
//					return;
//				}
//			}
//		}
		req.getRequestDispatcher(REGISTER).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		IUserService service = new UserServiceImpl();
		boolean isSuccess = service.register(username, email, password, fullname);
		if (isSuccess) {
			System.out.print("Thanh cong dang ky");
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			System.out.print("Login that bai!! ");
			req.setAttribute("error", "Đăng ký thất bại!!ERROR");
			req.getRequestDispatcher(REGISTER).forward(req, resp);
		}

	}

}