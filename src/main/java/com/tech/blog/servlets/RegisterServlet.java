package com.tech.blog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

@MultipartConfig
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
				IOException {
			String check = request.getParameter("check");
			if(check == null)
				response.getWriter().print("Accept terms and conditions");
			else {
				System.out.println(" checked");
				String name = request.getParameter("user_name");
				String password = request.getParameter("user_password");
				String gender = request.getParameter("gender");
				String email = request.getParameter("user_email");
				String about = request.getParameter("about");
				
				User u = new User();
				u.setName(name);
				u.setPassword(password);
				u.setEmail(email);
				u.setGender(gender);
				u.setAbout(about);
			
				UserDao udao = new UserDao(ConnectionProvider.getConnection());
				
				
				if(udao.saveUser(u)) {
					response.getWriter().print("done");
				}else {
					response.getWriter().print("Error in server");
				}
			}
	}

}
