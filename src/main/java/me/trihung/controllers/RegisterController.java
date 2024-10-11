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
		String phone = req.getParameter("phone");
		IUserService service = new UserServiceImpl();
		if (!service.isValidPassword(password)) {
			req.setAttribute("color", "red");
			req.setAttribute("error2", "Mật khẩu phải từ 8-20 kí tự (chữ cái thường, hoa, số, kí tự đặc biệt)!");
			req.setAttribute("type", "Register");
			req.getRequestDispatcher("mainhome.jsp").forward(req, resp);
			return;
		}

		boolean isSuccess = service.register(username, email, password, fullname, phone);
		if (isSuccess) {
			System.out.print("Thanh cong dang ky cho user: " + username);
			req.setAttribute("color", "green");
			req.setAttribute("error", "Đăng ký thành công, bạn vui lòng đăng nhập lại!");
			req.setAttribute("type", "Login");

		} else {
			System.out.println("Dang ky that bai cho user: " + username);
			req.setAttribute("color", "red");
			req.setAttribute("error2", "Đăng ký thất bại do email hoặc username đã tồn tại");
			req.setAttribute("type", "Register");
		}
		req.getRequestDispatcher("mainhome.jsp").forward(req, resp);


	}

}