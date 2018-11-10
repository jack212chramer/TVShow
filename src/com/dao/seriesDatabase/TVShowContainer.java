package com.dao.seriesDatabase;

import java.util.ArrayList;

import com.dao.PaginatorContainer;

public class TVShowContainer extends PaginatorContainer{
	private ArrayList<TVShow> list;
	private int sortBy;
	private int allShowsInDatabase;
	private String username;
	private String user_id;
	
	public String getUsername() {
		return username;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TVShowContainer(ArrayList<TVShow> list){
		this.list=list;
	}
	
	public ArrayList<TVShow> getList() {
		return list;
	}
	public void setList(ArrayList<TVShow> tvShowList) {
		this.list = tvShowList;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public int getAllShowsInDatabase() {
		return allShowsInDatabase;
	}

	public void setAllShowsInDatabase(int allShowsInDatabase) {
		this.allShowsInDatabase = allShowsInDatabase;
	}

	
	
}
