package com.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.dao.userDatabase.UserRegistering;
import com.dao.userDatabase.User;
import com.dao.userDatabase.UserJDBC;

@Service
public class LoginOrRegisterService {
	
	@Autowired
	private UserRegistering userRegistering;
	@Autowired
	private UserJDBC userJDBC;


	public ModelAndView registerNewUser(User userDto,HttpSession session){
		  if( userRegistering.validateAndRegister(userDto)){
			  	Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword(),
		         AuthorityUtils.createAuthorityList("USER"));
			  	SecurityContextHolder.getContext().setAuthentication(authentication);
	        	return login(userDto,session);
	        }else{
	        	return new ModelAndView("Error");
	        }
	}
	
	public ModelAndView login(User user,HttpSession session){
				
		String id = userJDBC.getUserId(user.getUsername());
		User user_logged_in = userJDBC.getUser(id);
		if(user_logged_in != null){
			if(userNotBanned(user_logged_in)){
				 session.setAttribute("user",user_logged_in.getUsername());
		            session.setAttribute("user_id",user_logged_in.getId());
		            session.setAttribute("user_privileges",user_logged_in.getPrivileges());
				   return new ModelAndView("redirect:/homepage","userDto",user_logged_in);
			}else{
				 return new ModelAndView("login","message","You have been banned, your account will be unlocked in "+user_logged_in.getBannedForDays()+" days.");
			}
           
	   }else{
		   return new ModelAndView( "login","message","Invalid username or password.");
	   }		
	}
	
	public boolean userNotBanned(User user){
		if(user.getBannedForDays()==0){
			return true;
		}else{
			return false;
		}
	}
	
	public String logout(HttpSession session){
		  session.removeAttribute("user");
		   session.removeAttribute("user_id");
		   session.removeAttribute("user_privileges");
	        session.invalidate();
		      return "redirect:/homepage";
	}
}
