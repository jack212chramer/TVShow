package com.dao.userDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.seriesDatabase.SqlValidator;

public class UserRegistering {
	
	
	@Autowired
	private UserJDBC userJDBC;
	
	private User user;
	
	public  boolean validateAndRegister(User user){
		this.user = user;
		boolean valid = false;

		if(validateUsername()&&validatePassword()&&validateEmail())
		{
			valid = true;
			registerNewUser();
		}
		return valid;
	}
	
public boolean validateUsername(){
		boolean valid = false;
		String username = user.getUsername();
		username = SqlValidator.SQLInjectionEscape(username, true);
		user.setUsername(username);
		if(username.matches("^[a-zA-Z0-9]{3,}$")){
			valid = true;
		}		
		if(userJDBC.checkIfUsernameAlreadyUsed(username)){
			valid = false;
		}
		return valid;
	}	
	
	public boolean validatePassword(){
		boolean valid = false;
		String password = user.getPassword();
		password = SqlValidator.SQLInjectionEscape(password, true);
		user.setPassword(password);
		if(password.matches("^(?=.*\\d).{6,}$")){
			valid = true;
		}
		return valid;
	}
	
	public boolean validateEmail(){
		boolean valid = false;
		String email = user.getEmail();
		email = SqlValidator.SQLInjectionEscape(email, true);
		user.setEmail(email);
		if(email.matches("^\\S+@\\S+$")){
			valid = true;
		}
		return valid;
	}
	
	
	public  void registerNewUser(){
		
		userJDBC.addUserToDatabase(user);
	}
}
