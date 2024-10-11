package me.trihung.controllers.manager;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.trihung.dao.impl.UserDAOImpl;
import me.trihung.dao.impl.UserServiceImpl;
import me.trihung.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/profile_account")
@MultipartConfig	
public class ProfileAccountController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		System.out.println("profile");

		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			String imgenc = user.getImages();
			if (imgenc==null||imgenc.length()==0) {
				imgenc = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAuAC4DASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD6DooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD//Z";
			}else {
				imgenc = user.getImages();
			}
			req.setAttribute("username", user.getUsername());
			req.setAttribute("imgencode", imgenc);
			req.setAttribute("fullname", user.getFullname());
			req.setAttribute("phonenumber", user.getPhone());
			req.removeAttribute("color1");
			req.removeAttribute("msg1");
			req.getRequestDispatcher("/profile_account.jsp").forward(req, resp);
			return;
		}
		goHomePage(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("call update");
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			String update = req.getParameter("button");
			UserDAOImpl dao = new UserDAOImpl();
			UserModel user = (UserModel) session.getAttribute("usermodel");
			if (update.equals("update1")) {
				String fullname = req.getParameter("fullnameinput");
				String phone = req.getParameter("phonenumberinput");
				user.setPhone(phone);
				user.setFullname(fullname);
			}else if (update.equals("update2")) {
				String path = req.getParameter("hiddeninput");
				path = path.split("base64,")[1];
				user.setImages(path);
				System.out.println("update img "+user.getImages());
			}else if (update.equals("update3")) {
				//update pass here
				String oldpass = req.getParameter("oldpass");
				String newpass = req.getParameter("newpass");
				String newpass2 = req.getParameter("newpass2");
				if (newpass.equals(newpass2)) {
					UserServiceImpl usi = new UserServiceImpl();
					if (usi.isValidPassword(newpass2)) {
						if (dao.changePasswordByOldPass(user.getUsername(), oldpass, newpass2)) {
							req.setAttribute("color1", "green");
							req.setAttribute("msg1", "Đổi mật khẩu thành công");
							user.setPassword(newpass2);
						}else {
							req.setAttribute("color1", "red");
							req.setAttribute("msg1", "Mật khẩu cũ không chính xác");
						}
					}else {
						req.setAttribute("color1", "red");
						req.setAttribute("msg1", "Mật khẩu mới phải từ 8-20 kí tự (chữ cái thường, hoa, số, kí tự đặc biệt)!");
					}
					
				}else {
					req.setAttribute("color1", "red");
					req.setAttribute("msg1", "Cập nhập dữ liệu thất bại!, mật khẩu mới nhập lại không chính xác");
				}
			}
			session.setAttribute("usermodel", user);
			
			
			String imgenc = user.getImages();
			if (imgenc==null||imgenc.length()==0) {
				imgenc = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAuAC4DASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD6DooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD//Z";
			}else {
				imgenc = user.getImages();
			}
			req.setAttribute("username", user.getUsername());
			req.setAttribute("imgencode", imgenc);
			req.setAttribute("fullname", user.getFullname());
			req.setAttribute("phonenumber", user.getPhone());
			//update to sql
			if (update.equals("update3")) {
				req.getRequestDispatcher("/profile_account.jsp").forward(req, resp);
				return;
			}
			if (dao.updateInfo(user)) {
				req.setAttribute("color1", "green");
				req.setAttribute("msg1", "Cập nhập dữ liệu thành công!");
				System.out.println("update sql complete");
			}else {
				req.setAttribute("color1", "red");
				req.setAttribute("msg1", "Cập nhập dữ liệu thất bại!");
				System.out.println("update sql fail");
			}
			req.getRequestDispatcher("/profile_account.jsp").forward(req, resp);
			return;
		}
		goHomePage(req, resp);
		
	}

	public void goHomePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}