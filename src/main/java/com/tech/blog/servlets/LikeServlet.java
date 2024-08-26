package com.tech.blog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.dao.LikeDao;
import com.tech.blog.helper.ConnectionProvider;

public class LikeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int userId =Integer.parseInt( request.getParameter("userId"));
			int postId = Integer.parseInt( request.getParameter("postId"));
			String operation =  request.getParameter("operation");
			
			LikeDao lDao = new LikeDao(ConnectionProvider.getConnection());
			
			if(operation.equals("like")) {
				
					boolean likeByUser = lDao.isLikeByUser(userId, postId);
					if(!likeByUser) {
						boolean insertLike = lDao.insertLike(userId, postId);
						response.getWriter().println(insertLike);
					}else {
						response.getWriter().println("You have already liked this post");
					}
						
					
			}else if(operation.equals("dislike")) {
				boolean disLike = lDao.disLike(userId, postId);
				if(disLike) {
					response.getWriter().println(disLike);
				}else {
					response.getWriter().println("Error in server");
				}
			}
			
	}

}
