package com.dao.userRatings;

import java.util.ArrayList;

import com.dao.seriesDatabase.TVShow;

public interface UserRatingDao {
	

	public  String getShowStatus(String tmdb_id,String user_id);
	
	public  void setShowStatus(String tmdb_id,String user_id,String status);
	
	public  boolean idVerification(String tmdb_id,String user_id);
	
	public  void deleteShowStatus(String tmdb_id,String user_id);
	
	public  String getRating(String tmdb_id,String user_id);
	
	public  void updateRating(String tmdb_id,String user_id,String rating);
	
	public  void updateShowStatus(String tmdb_id,String user_id,String status);
	
	public  void insertShowStatus(String tmdb_id,String user_id,String status);
	
	public ArrayList<Double> getAllRatings(int tmdb_id);	
	
	public  ArrayList<TVShow> getRecentlyWatched(String user_id);

	public ArrayList<TVShow> getHighestRated(String user_id,int limit,int offset);
}
