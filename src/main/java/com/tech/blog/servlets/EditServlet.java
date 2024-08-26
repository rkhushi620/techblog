package com.tech.blog.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.helper;

@MultipartConfig
public class EditServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String email = request.getParameter("user_email");
			String name =  request.getParameter("user_name");
			String password = request.getParameter("user_password");
			String about = request.getParameter("user_about");
			Part p = request.getPart("image");
			
			//get user from session
			HttpSession s = request.getSession();
			User user  =(User) s.getAttribute("currentUser");
			String oldImageName = user.getProfile();
			System.out.println("old image name : "+oldImageName);
			
			user.setName(name);
			user.setAbout(about);
			user.setPassword(password);
			user.setEmail(email);
			String imageName = user.getId()+"-"+p.getSubmittedFileName();
			user.setProfile(imageName);
			
			UserDao uDao = new UserDao(ConnectionProvider.getConnection());
			
			boolean y = uDao.updateUser(user);
			
			if(y) {
				String oldPath = request.getRealPath("/")+"pics"+File.separator+oldImageName;
				String newPath = request.getRealPath("/")+"pics"+File.separator+imageName;
				
				if(!oldImageName.equals("default.png"))
				helper.deleteFile(oldPath);
						
				if(helper.saveFile(p.getInputStream(), newPath)) {
					Message m = new Message("Updated Succesfully", "sucess", "alert-success");
					s.setAttribute("msg", m);			
				}else {
					Message m = new Message("Not Succesfully update profile", "error", "alert-danger");
					s.setAttribute("msg", m);
				}
				}else {
					Message m = new Message("Not Succesfully update profile", "error", "alert-danger");
					s.setAttribute("msg", m);
			}
			
			response.sendRedirect("profile.jsp");
	}

}
