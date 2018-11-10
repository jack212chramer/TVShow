package com.dao.seriesDatabase;

import java.util.ArrayList;

public class Season {
	
	private ArrayList<Episode> list;
	private int seasonNumber;
	
	public Season(ArrayList<Episode> list){
		this.list = list;
	}

	public ArrayList<Episode> getList() {
		return list;
	}

	public void setList(ArrayList<Episode> list) {
		this.list = list;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
	
	

}
