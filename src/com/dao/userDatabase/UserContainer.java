package com.dao.userDatabase;

import java.util.ArrayList;

import com.dao.PaginatorContainer;

public class UserContainer extends PaginatorContainer{
	private ArrayList<User> list;
	

	/**
	 * @param userList
	 */
	public UserContainer(ArrayList<User> userList) {
		super();
		this.list = userList;
	}
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> userList) {
		this.list = userList;
	}
		
	
}
