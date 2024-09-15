package me.trihung.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.trihung.dao.UserServiceImpl;
import me.trihung.models.UserModel;
import me.trihung.services.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

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
		req.removeAttribute("error2");
		req.setAttribute("type", "Register");
		req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
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
			System.out.print("Thanh cong dang ky cho user: "+username);
			req.setAttribute("color", "green");
			req.setAttribute("error", "Đăng ký thành công, bạn vui lòng đăng nhập lại!");
			req.setAttribute("type", "Login");
			req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
			
		} else {
			System.out.println("Dang ky that bai cho user: "+username);
			req.setAttribute("color", "red");
			req.setAttribute("error2", "Đăng ký thất bại do email hoặc username đã tồn tại");
			req.setAttribute("type", "Register");
			req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
		}

	}
	

}