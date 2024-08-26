package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;

public class PostDao {
	private Connection con;

	public PostDao(Connection c) {
		this.con = c;
	}

	public PostDao() {

	}

	public boolean savePost(Post post) {
		boolean f = false;

		try {
			String query = "insert into post(p_title,p_content,p_pic,p_code,c_id,u_id) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, post.getpTitle());
			ps.setString(2, post.getpContent());
			ps.setString(3, post.getpPic());
			ps.setString(4, post.getpCode());
			ps.setInt(5, post.getcId());
			ps.setInt(6, post.getuId());
			ps.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> cList = new ArrayList<Category>();

		String query = "select * from category";
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Category c = new Category(rs.getInt("c_id"), rs.getString("c_name"), rs.getString("c_description"));
				cList.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cList;
	}

	public ArrayList<Post> getAllPost() {
		ArrayList<Post> allPost = new ArrayList<Post>();

		try {

			String query = "Select * from post";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Post p = new Post();
				p.setpId(rs.getInt("p_id"));
				p.setpTitle(rs.getString("p_title"));
				p.setcId(rs.getInt("c_id"));
				p.setpCode(rs.getString("p_code"));
				p.setpContent(rs.getString("p_content"));
				p.setpDate(rs.getTimestamp("p_date"));
				p.setpPic(rs.getString("p_pic"));
				p.setuId(rs.getInt("u_id"));
				allPost.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allPost;
	}

	public ArrayList<Post> getAllPostByCattegory(int id) {
		ArrayList<Post> allPost = new ArrayList<Post>();

		try {

			String query = "Select * from post where c_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setpId(rs.getInt("p_id"));
				p.setpTitle(rs.getString("p_title"));
				p.setcId(rs.getInt("c_id"));
				p.setpCode(rs.getString("p_code"));
				p.setpContent(rs.getString("p_content"));
				p.setpDate(rs.getTimestamp("p_date"));
				p.setpPic(rs.getString("p_pic"));
				p.setuId(rs.getInt("u_id"));
				allPost.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allPost;
	}

	public Post getAllPostByPostId(int id) {
		Post p = new Post();
		try {

			String query = "Select * from post where p_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			
				p.setpId(rs.getInt("p_id"));
				p.setpTitle(rs.getString("p_title"));
				p.setcId(rs.getInt("c_id"));
				p.setpCode(rs.getString("p_code"));
				p.setpContent(rs.getString("p_content"));
				p.setpDate(rs.getTimestamp("p_date"));
				p.setpPic(rs.getString("p_pic"));
				p.setuId(rs.getInt("u_id"));
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}
	
	public ArrayList<Post> getAllPostOfUser(int uId){
		ArrayList<Post> allPost = new ArrayList<Post>();

		try {

			String query = "Select * from post where u_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, uId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setpId(rs.getInt("p_id"));
				p.setpTitle(rs.getString("p_title"));
				p.setcId(rs.getInt("c_id"));
				p.setpCode(rs.getString("p_code"));
				p.setpContent(rs.getString("p_content"));
				p.setpDate(rs.getTimestamp("p_date"));
				p.setpPic(rs.getString("p_pic"));
				p.setuId(rs.getInt("u_id"));
				allPost.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allPost;
	}

}
