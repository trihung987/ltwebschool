package me.trihung.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    resp.setContentType("text/html");
		    PrintWriter out = resp.getWriter();
		    // HTML page
		    out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Hello World</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h1>Xin chào!</h1>");
		    out.println("</body>");
		    out.println("</html>");

	}
}