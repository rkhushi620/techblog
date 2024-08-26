
<%@page import="com.tech.blog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="com.tech.blog.entities.User"%>
<%
		HttpSession s = request.getSession();
		User u = (User)s.getAttribute("currentUser");
%>
<!--profile modal-->

<!-- Modal -->
<div class="modal fade" id="profile-modal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header primary-background text-white text-center">
				<h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container text-center">
					<img src="pics/<%=u.getProfile()%>" class="img-fluid"
						style="border-radius: 50%; max-width: 150px;"> <br>
					<h5 class="modal-title mt-3" id="exampleModalLabel">
						<%=u.getName()%>
					</h5>
					<!--//details-->

					<div id="profile-details">
						<table class="table">

							<tbody>
								<tr>
									<th scope="row">ID :</th>
									<td>USER-<%=u.getId()%></td>

								</tr>
								<tr>
									<th scope="row">Email :</th>
									<td><%=u.getEmail()%></td>

								</tr>
								<tr>
									<th scope="row">Gender :</th>
									<td><%=u.getGender()%></td>

								</tr>
								<tr>
									<th scope="row">Status :</th>
									<td><%=u.getAbout()%></td>

								</tr>
								<tr>
									<th scope="row">Registered on :</th>
									<td><%=u.getDateTime().toString()%></td>

								</tr>
							</tbody>
						</table>
					</div>

					<!--profile edit-->

					<div id="profile-edit" style="display: none;">
						<h3 class="mt-2">Please Edit Carefully</h3>
						<form action="EditServlet" method="post"
							enctype="multipart/form-data">
							<table class="table">
								<!--                                         <tr> -->
								<!--                                             <td>ID :</td> -->
								<%--                                             <td><%= user.getId()%></td> --%>
								<!--                                         </tr> -->
								<tr>
									<td>Email :</td>
									<td><input type="email" class="form-control"
										name="user_email" value="<%=u.getEmail()%>" readonly>
									</td>
								</tr>
								<tr>
									<td>Name :</td>
									<td><input type="text" class="form-control"
										name="user_name" value="<%=u.getName()%>"></td>
								</tr>
								<tr>
									<td>Password :</td>
									<td><input type="text" class="form-control"
										name="user_password" value="<%=u.getPassword()%>">
									</td>
								</tr>
								<tr>
									<td>Gender :</td>
									<td><%=u.getGender().toUpperCase()%></td>
								</tr>
								<tr>
									<td>About :</td>
									<td><textarea rows="3" class="form-control"
											name="user_about"><%=u.getAbout()%>
                                                </textarea></td>
								</tr>
								<tr>
									<td>New Profile:</td>
									<td><input type="file" name="image" class="form-control">
									</td>
								</tr>

							</table>

							<div class="container">
								<button type="submit" class="btn btn-outline-primary">Save</button>
							</div>

						</form>

					</div>

				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button id="edit-profile-button" type="button"
					class="btn btn-primary">EDIT</button>
			</div>
		</div>
	</div>
</div>


<!--end of profile modal-->

<!--add post modal-->



<!-- Modal -->
<div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Provide the post
					details..</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form id="add-post-form" action="AddPostServlet" method="post"
					enctype="multipart/form-data">

					<div class="form-group">
						<select class="form-control" name="cid">
							<option selected disabled>---Select Category---</option>

							<%
							PostDao postd = new PostDao(ConnectionProvider.getConnection());
							ArrayList<Category> list = postd.getAllCategories();
							for (Category c : list) {
							%>
							<option value="<%=c.getCid()%>"><%=c.getName()%></option>

							<%
							}
							%>
						</select>
					</div>

					<div class="form-group">
						<input name="pTitle" type="text" placeholder="Enter post Title"
							class="form-control" />
					</div>

					<div class="form-group">
						<textarea name="pContent" class="form-control"
							style="height: 200px;" placeholder="Enter your content"></textarea>
					</div>
					<div class="form-group">
						<textarea name="pCode" class="form-control" style="height: 200px;"
							placeholder="Enter your program (if any)"></textarea>
					</div>
					<div class="form-group">
						<label>Select your pic..</label> <br> <input type="file"
							name="pic">
					</div>

					<div class="container text-center">
						<button type="submit" class="btn btn-outline-primary">Post
						</button>
					</div>

				</form>


			</div>

		</div>
	</div>
</div>


<!--END add post modal-->