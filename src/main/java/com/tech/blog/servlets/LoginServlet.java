package com.tech.blog.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		
		UserDao uDao = new UserDao(ConnectionProvider.getConnection());
		
		User user = uDao.getUserByEmailAndPassword(userEmail, userPassword);
		
		if(user == null) {
			//login error
			Message m = new Message("Invalid Details.","error","alert-danger");
			 HttpSession session = request.getSession();
			 session.setAttribute("msg", m);
			 response.sendRedirect("login_page.jsp");
		}else {
			 HttpSession session = request.getSession();
			 session.setAttribute("currentUser", user);
			 response.sendRedirect("profile.jsp");
		}
	}

}
