package com.tech.blog.entities;

import java.sql.Timestamp;

public class Post {
	private int pId;
	private String pTitle;
	private String pContent;
	private String pCode;
	private String pPic;
	private Timestamp pDate;
	private int uId;
	private int cId;
	
	
	public Post() {
		
	}
	
	







	public Post(int pId, String pTitle, String pContent, String pCode, String pPic, Timestamp pDate, int uId, int cId) {
		super();
		this.pId = pId;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pCode = pCode;
		this.pPic = pPic;
		this.pDate = pDate;
		this.uId = uId;
		this.cId = cId;
	}







	public Post(String pTitle, String pContent, String pCode, String pPic, Timestamp pDate, int uId, int cId) {
		super();
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pCode = pCode;
		this.pPic = pPic;
		this.pDate = pDate;
		this.uId = uId;
		this.cId = cId;
	}









	public int getpId() {
		return pId;
	}




	public void setpId(int pId) {
		this.pId = pId;
	}




	public String getpTitle() {
		return pTitle;
	}




	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}




	public String getpContent() {
		return pContent;
	}




	public void setpContent(String pContent) {
		this.pContent = pContent;
	}




	public String getpCode() {
		return pCode;
	}




	public void setpCode(String pCode) {
		this.pCode = pCode;
	}




	public String getpPic() {
		return pPic;
	}




	public void setpPic(String pPic) {
		this.pPic = pPic;
	}




	public Timestamp getpDate() {
		return pDate;
	}




	public void setpDate(Timestamp pDate) {
		this.pDate = pDate;
	}




	public int getcId() {
		return cId;
	}




	public void setcId(int cId) {
		this.cId = cId;
	}









	public int getuId() {
		return uId;
	}









	public void setuId(int uId) {
		this.uId = uId;
	}
	
	
	
	
	
}
