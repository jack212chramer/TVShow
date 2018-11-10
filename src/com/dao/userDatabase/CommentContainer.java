package com.dao.userDatabase;

import java.util.ArrayList;

import com.dao.PaginatorContainer;

public class CommentContainer extends PaginatorContainer{

	private ArrayList<Comment> list;
	private int id;
	private int type;// 0 - show, 1 - news

	/**
	 * @param list
	 */
	public CommentContainer(ArrayList<Comment> list) {
		super();
		this.list = list;
	}

	public ArrayList<Comment> getList() {
		return list;
	}

	public void setList(ArrayList<Comment> list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
