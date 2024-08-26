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

import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.helper;

@MultipartConfig
public class AddPostServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pTitle = request.getParameter("pTitle");
		String pContent = request.getParameter("pContent");
		String pCode = request.getParameter("pCode");
		Part p = request.getPart("pic");
		String pImageName = p.getSubmittedFileName();
		int cId = Integer.parseInt(request.getParameter("cid"));
		Post post = new Post();
		post.setcId(cId);
		post.setpTitle(pTitle);
		post.setpContent(pContent);
		post.setpCode(pCode);
	
		
		HttpSession s = request.getSession();
		User u =(User) s.getAttribute("currentUser");
		post.setuId(u.getId());
		post.setpPic(u.getId()+pImageName);
		PostDao pdao = new PostDao(ConnectionProvider.getConnection());
		boolean savePost = pdao.savePost(post);
		if(savePost) {
			//save successfully
			System.out.println(request.getRealPath("/"));
			String saveFilePath = request.getRealPath("/")+"pics"+ File.separator + u.getId()+pImageName;
			helper.saveFile(p.getInputStream(),saveFilePath );
			response.getWriter().println("done");
		}else {
			//error
			response.getWriter().println("Error in saving the file");
		}
	}

}
