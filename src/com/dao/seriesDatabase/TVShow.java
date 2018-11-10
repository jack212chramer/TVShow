package com.dao.seriesDatabase;

public class TVShow {
	private int tmdb_id;
	private String poster_path;
	private String name;
	private double rating;
	private String  original_name;
	private String  first_air_date;
	private int  popularity;
	private String  original_language;
	private String  backdrop_path;
	//only by setters:
	private String overview;
	private String homepage;
	private int number_of_episodes;
	private int number_of_seasons;
	private String  last_air_date;
	private String next_air_date;
		
	
	
	public TVShow(){}
	
	public TVShow(int id,String poster_path,double rating,String title){
		this.tmdb_id=id;
		this.poster_path=poster_path;
		this.name=title;
		this.rating=rating;
	}
	
	public TVShow(int tmdb_id, String poster_path, String name, double rating, String original_name,
			String first_air_date, int popularity, String original_language, String backdrop_path,String next_air_date) {
		super();
		this.tmdb_id = tmdb_id;
		this.poster_path = poster_path;
		this.name = name;
		this.rating = rating;
		this.original_name = original_name;
		this.first_air_date = first_air_date;
		this.popularity = popularity;
		this.original_language = original_language;
		this.backdrop_path = backdrop_path;
		this.next_air_date=next_air_date;
	}
	
	
	public int getTmdb_id() {
		return tmdb_id;
	}
	public void setTmdb_id(int tmdb_id) {
		this.tmdb_id = tmdb_id;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public String getRatingFormatted() {
		java.text.DecimalFormat df=new java.text.DecimalFormat("0.0#");
		return df.format(rating);
	}
	public String getRatingFormatted2() {
		java.text.DecimalFormat df=new java.text.DecimalFormat("0.##");
		return df.format(rating);
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getFirst_air_date() {
		return first_air_date;
	}
	public void setFirst_air_date(String first_air_date) {
		this.first_air_date = first_air_date;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public int getNumber_of_episodes() {
		return number_of_episodes;
	}
	public void setNumber_of_episodes(int number_of_episodes) {
		this.number_of_episodes = number_of_episodes;
	}
	public int getNumber_of_seasons() {
		return number_of_seasons;
	}
	public void setNumber_of_seasons(int number_of_seasons) {
		this.number_of_seasons = number_of_seasons;
	}
	public String getLast_air_date() {
		return last_air_date;
	}
	public void setLast_air_date(String last_air_date) {
		this.last_air_date = last_air_date;
	}

	public String getNext_air_date() {
		return next_air_date;
	}

	public void setNext_air_date(String next_air_date) {
		this.next_air_date = next_air_date;
	}

	


	

	



	

}
