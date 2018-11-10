package com.dao.news;

import java.util.ArrayList;

public interface NewsDao {

	public int getNewsCount();
	
	public ArrayList<News> getNewsList(int limit,int offset);
	
	public News getNewsWithId(int id);
	
	public void insertNews(News news);
	
	public void deleteNews(int news_id);
	
	public void updateNews(int id,News news);
}
