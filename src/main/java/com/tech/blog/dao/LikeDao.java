package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDao {
		Connection con;
		public LikeDao(Connection con) {
			this.con = con;
		}
		
		public boolean insertLike(int user_id,int post_id) {
			boolean f =false;
			try {
				
				String query = "insert into like_table(u_id,p_id) values(?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, user_id);
				ps.setInt(2, post_id);
				ps.executeUpdate();
				f = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return f;
		}
		public int  getLikeOfPost(int post_id) {
			int l= 0;
			try {
				
				String query = "select count(*) as no_of_like from like_table where p_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, post_id);
				ResultSet rs = ps.executeQuery();
				rs.next();
				
				l = rs.getInt("no_of_like");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return l;
		}
		
		public boolean isLikeByUser(int user_id ,int post_id) {
			boolean f =false;
			try {
				
				String query = "select * from  like_table where u_id = ? and p_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, user_id);
				ps.setInt(2, post_id);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					f = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return f;
		}
		
		public boolean disLike(int user_id,int post_id) {
			
			boolean f =false;
			try {
				
				String query = "delete  from  like_table where u_id = ? and p_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, user_id);
				ps.setInt(2, post_id);
				ps.executeUpdate();
				f = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return f;
			
		}

}
