package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.userDatabase.User;
import com.dao.userDatabase.UserJDBC;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	  @Autowired
	  UserJDBC userJDBC;
	  
	  
	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		  
	    User user = findUserbyUername(username);
	    
	    UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(user.getPassword());
	      builder.roles(user.getRoles());
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
	    return builder.build();
	  }

	  private User findUserbyUername(String username) {
	     String id = userJDBC.getUserId(username);
	     User userdb = userJDBC.getUser(id);
	     if(userdb.getPrivileges()==0){
		     userdb.setRoles("USER");
	     }else{
		     userdb.setRoles("USER","ADMIN");
	     }
	     return	userdb;
	  }
	}