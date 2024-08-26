<%@page import="com.tech.blog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="com.tech.blog.entities.User"%>
<%
HttpSession se = request.getSession();
User u = (User) se.getAttribute("currentUser");
%>



<%
if (u == null) {
%>
<nav class="navbar navbar-expand-lg navbar-dark primary-background">
	<a class="navbar-brand" href="index.jsp"> <span
		class="fa fa-home"></span> Tech Blog
	</a>
	<div class="collapse navbar-brand navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto"></ul>
		<ul class="navbar-nav mr-right">
			<li class="nav-item"><a class="nav-link" href="login_page.jsp">
					<span class="fa fa-user-circle "></span> Login
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="register_page.jsp"> <span class="fa fa-user-plus "></span>Sign up
			</a></li>
		</ul>
	</div>
</nav>
<%
} else {
%>
<!-- 				user login -->
<nav class="navbar navbar-expand-lg navbar-dark primary-background">


	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="profile.jsp">
					<span class="fa fa-home"></span> Home
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <span class="fa fa-list"></span> Category
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				<% 
					PostDao d = new PostDao(ConnectionProvider.getConnection());
                	ArrayList<Category> list1 = d.getAllCategories();
                	for(Category c : list1)
                	{
                %>
                	<a class="dropdown-item " onclick="getPosts(<%= c.getCid()%>)"><%= c.getName() %></a> 
                <%
                	}
				%>
					
				</div></li>

		</ul>
		<ul class="navbar-nav mr-right">

			<li class="nav-item"><a class="nav-link"
				href="user_post_page.jsp"> <span class="fa fa-history" ></span>
					Your Post
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#add-post-modal"> <span
					class="fa fa-plus"></span> Add Post
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#!"
				data-toggle="modal" data-target="#profile-modal"> <span
					class="fa fa-user-circle "></span> <%=u.getName()%>
			</a></li>

			<li class="nav-item"><a class="nav-link" href="LogoutServlet">
					<span class="fa fa-user-plus "></span> Logout
			</a></li>
		</ul>
	</div>


</nav>
<%
}
%>






