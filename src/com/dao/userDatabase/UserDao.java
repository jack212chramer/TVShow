package com.dao.userDatabase;

import java.util.ArrayList;

public interface UserDao {
	
	public boolean addUserToDatabase(User user);
	public User getUser(String id);
	public boolean checkIfUsernameAlreadyUsed(String username);
	public String getUserId(String username);
	public User loginUser(User user);
	public void updateUser(User user);
	public ArrayList<User> getUsers(String sqlCondition);
}
