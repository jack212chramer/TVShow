package com.dao.seriesDatabase;

import java.util.ArrayList;

public interface SeriesDao {
	
	public TVShow searchById(int id);
	
	public ArrayList<TVShow> searchByName(String input,int limit,int offset);
	
	public int searchByNameCount(String input);
	/**	type:
	 * 		0- sorted by popularity
	 *		1- sorted by vote_average
	 *		2- sorted by name
	 *		3- sorted by language
	 *		4- sorted by number of episodes
	 *		5- sorted by number of seasons
	 *		
	 *		limit : -1 is unlimited
	 */	
	public ArrayList<TVShow> getShows(int limit,int type,int offset,boolean descending,String sqlCondition);
		
	public String parseYear(String data);
	
	public ArrayList<Integer> getAllTmdb_id();

	public void updateAverageRating(int tmdb_id,double average);
	
	/**
	 * 
	 * @param condition example: WHERE popularity>=15
	 * @return number of results
	 */
	public int countAllSeries(String condition);
	
	public void createShow(TVShow show);
	
	public void updateShow(TVShow show);
	
	public void deleteShow(TVShow show);
}
