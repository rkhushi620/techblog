<%@page import="com.tech.blog.entities.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>

<div class="row">

	<%
	Thread.sleep(500);
	PostDao pDao = new PostDao(ConnectionProvider.getConnection());
	int uId = Integer.parseInt(request.getParameter("u_Id"));
	ArrayList<Post> p = pDao.getAllPostOfUser(uId);
	
	if(p.size()==0){
		out.println("<h3 class = 'container display-3 text-center'>No Post</h3>");
		return;
	}
	for (Post post : p) {
	%>
	<div class="col-md-6 mt-3">
		<div class = "card">
			<img src="pics/<%= post.getpPic() %>" class="card-img-top" alt="..."  height="400px">
			<div class = card-body>
				<h3><%= post.getpTitle() %></h3>
<%-- 				<p><%= post.getpContent() %></p> --%>
<%-- 				<pre><%= post.getpCode() %></pre> --%>

			</div>
			<diV class = "card-footer primary-background text-center">
				<a href="#" class = "btn btn-outline-light"><i class="fa fa-trash"></i> Remove</a>
				
				<a href="#" class = "btn btn-outline-light"><i class="fa fa-pencil"><span> Edit</span></i></a>
			</div>
		</div>
	</div>
	<%
	}
	%>




</div>