package com.service.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {
	@Autowired
	RatingUpdater ratingUpdater;
	@Autowired
	BanUpdater banUpdater;
	
	
	@Scheduled(cron="0 00/60 * ? * *")
	public void ratingUpdate(){
		ratingUpdater.updateVoteAverage();
	}
	
	@Scheduled(cron="0 0 12 * * *", zone="Europe/Istanbul")
	public void banUpdate(){
		banUpdater.updateBannedUsers();
	}
	
	
	
	
	
	
	
}
