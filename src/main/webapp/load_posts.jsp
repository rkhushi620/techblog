<%@page import="com.tech.blog.entities.User"%>
<%@page import="com.tech.blog.dao.LikeDao"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>

<%@page errorPage="error_page.jsp" %>
<%

    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login_page.jsp");
    }
    
    
    


%>
<div class="row">

	<%
	Thread.sleep(500);
	PostDao pDao = new PostDao(ConnectionProvider.getConnection());
	int catId = Integer.parseInt(request.getParameter("cid"));
	ArrayList<Post> p = null;
	if(catId == 0){
		p = pDao.getAllPost();
	}else{
		p = pDao.getAllPostByCattegory(catId);
	}
	
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
				<% 
					LikeDao lDao = new LikeDao(ConnectionProvider.getConnection());
					
				%>
				<a onclick="doLike(<%= user.getId()%>,<%= post.getpId()%>,<%=post.getpId()%>)"class = "like-counter btn btn-outline-light"><i class="fa fa-heart-o"><span class ="font-weight-bold text-light"id = "<%=post.getpId()%>"> <%=lDao.getLikeOfPost(post.getpId())%></span>-likes</i></a>
				<a href="show_blog_page.jsp?post_id=<%= post.getpId()%>" class = "btn btn-outline-light">Read more</a>
<!-- 				<a href="#" class = "btn btn-outline-light"><i class = "fa fa-commenting-o"><span> 20</span></i></a> -->
			</div>
		</div>
	</div>
	<%
	}
	%>




</div>