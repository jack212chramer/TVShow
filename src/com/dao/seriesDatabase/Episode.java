package com.dao.seriesDatabase;

public class Episode {
	
	private int tmdb_id;
	private int season;
	private int episode_number;
	private String name;
	private String overview;
	private String air_date;
	
	public Episode(){}

	/**
	 * @param tmdb_id
	 * @param season
	 * @param episode_number
	 * @param name
	 * @param overview
	 * @param air_date
	 */
	public Episode(int tmdb_id, int season, int episode_number, String name, String overview, String air_date) {
		super();
		this.tmdb_id = tmdb_id;
		this.season = season;
		this.episode_number = episode_number;
		this.name = name;
		this.overview = overview;
		this.air_date = air_date;
	}

	public int getTmdb_id() {
		return tmdb_id;
	}

	public void setTmdb_id(int tmdb_id) {
		this.tmdb_id = tmdb_id;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode_number() {
		return episode_number;
	}

	public void setEpisode_number(int episode_number) {
		this.episode_number = episode_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getAir_date() {
		return air_date;
	}

	public void setAir_date(String air_date) {
		this.air_date = air_date;
	}
	
	
	

}
