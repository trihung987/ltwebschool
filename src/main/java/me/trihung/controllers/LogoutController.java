package me.trihung.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		session.removeAttribute("usermodel");
		goHomePage(req, resp);
		
	}

	public void goHomePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}