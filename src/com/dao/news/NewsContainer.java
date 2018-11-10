package com.dao.news;

import java.util.ArrayList;

import com.dao.PaginatorContainer;

public class NewsContainer extends PaginatorContainer{

	private ArrayList<News> list;
	
	public NewsContainer(ArrayList<News> newsList) {
		super();
		this.list = newsList;
	}

	public ArrayList<News> getList() {
		return list;
	}

	public void setList(ArrayList<News> list) {
		this.list = list;
	}
	
	

}
