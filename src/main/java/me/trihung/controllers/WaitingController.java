package me.trihung.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.trihung.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
				
//		// Check cookie
//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				session = req.getSession(true);
//				session.setAttribute(cookie.getName(), cookie.getValue());
//				ok = true;
//			}
//			
//		}
		boolean ok = false;

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			req.setAttribute("name", user.getFullname());
			if (!user.getRole().equals("MEMBER")) {
				resp.sendRedirect(req.getContextPath() + "/manager");
				return;
			}
				
			ok = true;
		}
		if (!ok) {
			// go back home page
			goHomePage(req, resp);
			return;
		}
		req.getRequestDispatcher("waiting.jsp").forward(req, resp);
		
	}

	public void goHomePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}