package com.dao.seriesDatabase;

import java.util.ArrayList;

public interface SeasonsDao {

	public ArrayList<Season> getSeasons(int tmdb_id);
	
	public int howManySeasons(int tmdb_id);
	
}
