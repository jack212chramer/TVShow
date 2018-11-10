package com.service.scheduling;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.seriesDatabase.SeriesJDBC;
import com.dao.userRatings.UserRatingJDBC;

@Service
public class RatingUpdater {
		
	@Autowired
	private SeriesJDBC seriesJDBC;
	@Autowired
	private UserRatingJDBC userRatingJDBC;
	
	private ArrayList<Integer> tmdb_idList;
	private ArrayList<Double> userVotesList;		
	
	
	public void updateVoteAverage(){
		System.out.println("Updating ratings...");
		tmdb_idList=seriesJDBC.getAllTmdb_id();
		//for every id
		for(int i=0;i<tmdb_idList.size();i++){
			double average=0;
			userVotesList=userRatingJDBC.getAllRatings(tmdb_idList.get(i));
			//sum all user ratings
			int numberOfVotes=0;
			for(int j=0;j<userVotesList.size();j++){
				if(userVotesList.get(j)==0){
					//skip
				}else{
					average+=userVotesList.get(j);
					numberOfVotes++;	
				}
			}
			if(numberOfVotes>0){
				average=average/numberOfVotes;
				seriesJDBC.updateAverageRating(tmdb_idList.get(i),average);
			}
		}
		System.out.println("Ratings update completed!");
	}
}
