package com.dao.userDatabase;

public class Comment {

	private int comment_id;
	private int user_id;
	private int id;	//element id like news_id or tmdb_id
	private String text;
	private String date;
	private String username;
	private int type; // 0 - show, 1 - news

	public Comment(){}
	
	
	public Comment(int comment_id, int user_id, int id, String text, String date, String username, int type) {
		super();
		this.comment_id = comment_id;
		this.user_id = user_id;
		this.id = id;
		this.text = text;
		this.date = date;
		this.username = username;
		this.type = type;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
