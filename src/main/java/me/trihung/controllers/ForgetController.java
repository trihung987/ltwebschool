package me.trihung.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.trihung.dao.UserServiceImpl;
import me.trihung.dao.impl.UserDAOImpl;
import me.trihung.services.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forget")
public class ForgetController extends HttpServlet {
	public static final String SESSION_USERNAME = "username";
	public HashMap<String, Long> mailTime = new HashMap<>();
	public HashMap<String, String> mailOTP = new HashMap<>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String button = req.getParameter("button");
		String mail = req.getParameter("email");
		String newpass = req.getParameter("password");
		String otp = req.getParameter("otp-code");
		if (!isExistMail(mail)) {
			req.setAttribute("color", "red");
			req.setAttribute("msg", "Mail không tồn tại!");
			reloadPage(mail, newpass, req, resp);
			return;
		}
		
		if (button.equals("otp")) {
			if (!canGetNewOTP(mail)) {
				req.setAttribute("color", "red");
				req.setAttribute("msg", "Vui lòng chờ "+(60-secondDiff(mail))+"s nữa trước khi lấy OTP mới!");
			}else {
				req.setAttribute("status", "block");
				req.setAttribute("color", "green");
				req.setAttribute("msg", "Vui lòng kiểm tra mail để lấy mã OTP");
				mailTime.remove(mail);
				mailOTP.remove(mail);
				//sendmail
				sendEmail(mail);
			}
		}else if (button.equals("submit")) {
			if (otp!=null && otp.strip().length()==6) {
				if (isOTPAndInTime(mail, otp)) {
					//change pass
					if (isValidPassword(newpass)) {
						if (changePassword(mail, newpass)) {
							req.setAttribute("color", "green");
							req.setAttribute("msg", "Đổi mật khẩu thành công!");
						}else {
							req.setAttribute("color", "red");
							req.setAttribute("msg", "Đã xảy ra lỗi!");
						}
					}else {
						req.setAttribute("color", "red");
						req.setAttribute("msg", "Mật khẩu phải từ 8-20 kí tự (chữ cái thường, hoa, số, kí tự đặc biệt)!");
					}
					
				}else {
					req.setAttribute("color", "red");
					req.setAttribute("msg", "OTP sai hoặc đã quá hạn 60s! Vui lòng lấy mã OTP mới");
				}
				mailOTP.remove(mail);
			}else {
				req.setAttribute("color", "red");
				req.setAttribute("msg", "OTP trống hoặc không đúng 6 ký tự!");
			}
		}
		reloadPage(mail, newpass, req, resp);
	}
	
	public Boolean isValidPassword(String password) {
		UserServiceImpl usi = new UserServiceImpl();
		return usi.isValidPassword(password);
	}
	
	public Boolean changePassword(String mail, String password) {
		UserDAOImpl dao = new UserDAOImpl();
		return dao.changePasswordByMail(mail, password);
	}
	
	public void reloadPage(String mail, String newpass, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (mail!=null)
			req.setAttribute("oldmail", mail);
		if (newpass!=null)
			req.setAttribute("oldpass", newpass);
		req.setAttribute("status", "none");
		req.getRequestDispatcher("forgetpass.jsp").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.removeAttribute("color");
		req.removeAttribute("msg");
		req.removeAttribute("oldmail");
		req.removeAttribute("oldpass");
		req.setAttribute("status", "none");
		req.getRequestDispatcher("forgetpass.jsp").forward(req, resp);
	}
	
	public Boolean isExistMail(String mail) {
		IUserService service = new UserServiceImpl();
		if (service.checkExistEmail(mail))
			return true;
		return false;
	}
	
	public Boolean canGetNewOTP(String mail) {
		if (!mailTime.containsKey(mail))
			return true;
		if (secondDiff(mail)> 60) 
			return true;
		return false;
	}
	
	public Boolean isOTPAndInTime(String mail, String otp) {
		if (mailTime.containsKey(mail)) {
			if (secondDiff(mail) <= 60 && mailOTP.containsKey(mail) && mailOTP.get(mail).equals(otp))
				return true;
		}
		return false;
	}

	public Long getTimeNow() {
		return new Date().getTime();
	}

	public long secondDiff(String mail) {
		return TimeUnit.SECONDS.convert(getTimeNow() - mailTime.get(mail), TimeUnit.MILLISECONDS);
	}

	public String generateSecretKey() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] bytes = new byte[16];

		secureRandom.nextBytes(bytes);
		StringBuilder secretKey = new StringBuilder();

		for (byte b : bytes) {
			secretKey.append(String.format("%02x", b));
		}

		return secretKey.toString();
	}

	public String generateOTP(String secretKey, int length) {
		String allowedCharacters = "0123456789";
		StringBuilder otp = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = secureRandom.nextInt(allowedCharacters.length());
			otp.append(allowedCharacters.charAt(randomIndex));
		}

		return otp.toString();
	}

	public void sendEmail(String send_to) throws UnsupportedEncodingException {

		final String mail = "trihungschool987@gmail.com";
		final String password = "pzfi savh azuf ebkf ";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail, password);
			}
		});

		try {
			String otp = generateOTP(generateSecretKey(), 6);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(send_to));
			message.setSubject("Web code otp");
			message.setContent(getContentMail(otp), "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("Send mail otp to mail " + send_to);
			//System.out.println(getContentMail(otp));
			mailTime.put(send_to, getTimeNow());
			mailOTP.put(send_to, otp);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public String getContentMail(String code) {
		try {
			/*
			 * String url = getServletContext().getResource("/mail/email.html").toString();
			 * File file = new File(url);
			 */
			InputStream is = getServletContext().getResourceAsStream("/mail/email.html");
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String str;
			StringBuilder content = new StringBuilder();
		    while ((str = in.readLine()) != null) {
		        content.append(str);
		    }
		    in.close();
		    String newcode = " ";
		    for(int i =0;i<code.length();i++) {
		    	newcode+=code.charAt(i)+" ";
		    }
		    return content.toString().replace("{OTP-CODE}", newcode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}