package com.service.scheduling;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.userDatabase.User;
import com.dao.userDatabase.UserJDBC;

@Component
public class BanUpdater {
	
	@Autowired
	UserJDBC userJDBC;
	
	public void updateBannedUsers(){
		System.out.println("Updating banned users...");
		ArrayList<User> bannedUsers = userJDBC.getUsers("WHERE banned_for_days > 0");
		int i =0;
		while(i<bannedUsers.size()){
			int bannedFor = bannedUsers.get(i).getBannedForDays();
			bannedUsers.get(i).setBannedForDays(bannedFor-1);
			userJDBC.updateUser(bannedUsers.get(i));
			i++;
		}
		System.out.println("Updating completed!");
	}

}
