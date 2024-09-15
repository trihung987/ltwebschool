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
import me.trihung.models.UserModel;
import me.trihung.services.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/manager")
public class ManagerController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean ok = false;

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			req.setAttribute("name", user.getFullname());
			if (!user.getRole().equals("MANAGER")) {
				resp.sendRedirect(req.getContextPath() + "/waiting");
				return;
			}
			ok = true;
		}
		if (!ok) {
			// go back home page
			System.out.println("back");
			goHomePage(req, resp);
			return;
		}
		req.getRequestDispatcher("manager.jsp").forward(req, resp);
	}
	
	public void goHomePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/login");
	}

}