<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="me.trihung.config.DBConnectSQLServer"%>
<%@ page import="java.sql.*"%>

<%
try {
	Connection connection = DBConnectSQLServer.getConnection();
	Statement st = connection.createStatement();
	String q1 = "create table production (id int,name varchar(200),category varchar(150), price int, active varchar(100))";
	System.out.println(q1);
	st.execute(q1);
	System.out.println(st);
	connection.close();
} catch (Exception e) {
	System.out.println(e);
}
%>