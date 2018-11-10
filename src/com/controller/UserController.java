package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.PaginatorDao;
import com.dao.userDatabase.Comment;
import com.dao.userDatabase.CommentJDBC;
import com.dao.userDatabase.User;
import com.dao.userDatabase.UserJDBC;
import com.service.AdminManager;
import com.service.DateAndTimeService;
import com.service.LoginOrRegisterService;

@Controller
public class UserController {
	
	@Autowired
	private LoginOrRegisterService loginOrRegister;
	@Autowired
	UserJDBC userJDBC;
	@Autowired
	PaginatorDao paginatorDao;
	@Autowired
	DateAndTimeService date;
	@Autowired
	AdminManager adminManager;
	@Autowired
	CommentJDBC commentJDBC;
	
		//	LOGIN PAGE
	   @RequestMapping(value="/login",method = RequestMethod.GET)
	   public String login() {
		      return "login";
		   }
	  
	   
	   //	LOGIN PROCESS
	   @RequestMapping(value="/loginCheck",method = RequestMethod.GET)
	   	public ModelAndView LoginCheck(HttpSession session) {
		   
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      String name = auth.getName();
		   User userDto = new User();
		   userDto.setUsername(name);
		   ModelAndView mav = loginOrRegister.login(userDto, session);

		   return mav;
		      
		   }
	   
	   //	LOGOUT
	   @RequestMapping(value="/Logout",method = RequestMethod.GET)
	   public String Logout(HttpSession session) {
		      return loginOrRegister.logout(session);
		   }
	   
	   //	REGISTER PAGE
	   @RequestMapping(value="/register",method = RequestMethod.GET)
	   public String register() {
		      return "register";
		   }
	  
	   //	REGISTER PROCESS
	   @RequestMapping(value="/registerCheck",method = RequestMethod.POST)
	   public ModelAndView registerCheck(
			   @RequestParam(value="username")String username,
			   @RequestParam(value="password")String password,
			   @RequestParam(value="email")String email,
			   HttpSession session) {
		   
		   String passwordEncoded = new BCryptPasswordEncoder().encode(password);
		   User userDto = new User(username,passwordEncoded,email);
		   	return loginOrRegister.registerNewUser(userDto, session); 
		   }
	   
	   //	userAlreadyExists
	   @RequestMapping(value="/userAlreadyExists",method = RequestMethod.GET)
	   @ResponseBody
	   public String userAlreadyExists(
			   @RequestParam(value="username")String username) {
		   String response = "0";
		   if(userJDBC.checkIfUsernameAlreadyUsed(username)){
			   response="1";
		   }
		   return response;
		   }
	   
	   //	SETTINGS
	   @RequestMapping(value="/settings",method = RequestMethod.GET)
	   public ModelAndView settings(HttpSession session) {
		   String id = session.getAttribute("user_id").toString();
		   User user = userJDBC.getUser(id);
		      return new ModelAndView("settings","userDto",user);
		   }
	   
	   // edit username
	   @RequestMapping(value="/editUsername",method = RequestMethod.POST)
	   public String editUsername(
			   @RequestParam(value="username")String new_username,
			   HttpSession session) {
		   
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      String username = auth.getName();
		   String id = userJDBC.getUserId(username);
		   User user = userJDBC.getUser(id);
		   user.setUsername(new_username);
		   userJDBC.updateUser(user);
		   loginOrRegister.login(user, session);
		   	return "redirect:/settings"; 
		   }
	   
	   // edit password
	   @RequestMapping(value="/editPassword",method = RequestMethod.POST)
	   public String editPassword(
			   @RequestParam(value="password")String new_password,
			   HttpSession session) {
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      String username = auth.getName();
		   String id = userJDBC.getUserId(username);
		   User user = userJDBC.getUser(id);
		   String passwordEncoded = new BCryptPasswordEncoder().encode(new_password);
		   user.setPassword(passwordEncoded);;
		   userJDBC.updateUser(user);
		   loginOrRegister.login(user, session);
		   	return "redirect:/settings"; 
		   }
	   
	   // edit email
	   @RequestMapping(value="/editEmail",method = RequestMethod.POST)
	   public String editEmail(
			   @RequestParam(value="email")String new_email,
			   HttpSession session) {
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      String username = auth.getName();
		   String id = userJDBC.getUserId(username);
		   User user = userJDBC.getUser(id);
		   user.setEmail(new_email);
		   userJDBC.updateUser(user);
		   loginOrRegister.login(user, session);
		   	return "redirect:/settings"; 
		   }
	   
	   @RequestMapping(value="/addComment",method = RequestMethod.POST)
	   public String addComment(
			   @RequestParam(value="id")int id,
			   @RequestParam(value="comment")String text,
			   @RequestParam(value="type")int type,
			   HttpSession session) {
			   try{
				   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				      String username = auth.getName();
				   String user_id = userJDBC.getUserId(username);
				   int user_id_int = Integer.parseInt(user_id);
				   Comment comment = new Comment(
						   0,user_id_int,id,text,date.getCurrentDateAndTime(), username,type
						   );
				   commentJDBC.addComment(comment);
			   }catch(NullPointerException e){
				   //ignore - user is not logged in
			   }
			   if(type==0){
				   return "redirect:/serial?tmdb_id="+id; 
			   }else if(type==1){
				   return "redirect:/news?news_id="+id; 
			   }else{
					return "Error";
			   }
		   }
	   
	   @RequestMapping(value="/deleteComment",method = RequestMethod.POST)
	   public String deleteComment(
			   @RequestParam(value="comment_id")int comment_id,
			   HttpSession session) {
		   int tmdb_id = 0;
		   int type = 0;
			   try{
				   Comment comment = commentJDBC.getComment(comment_id);
				   tmdb_id=comment.getId();
				   type = comment.getType();
				   adminManager.deleteComment(comment, session);
			   }catch(NullPointerException e){
				   //ignore - user is not logged in
			   }
			   if(type==0){
				   return "redirect:/serial?tmdb_id="+tmdb_id; 
			   }else if(type==1){
				   return "redirect:/news?news_id="+tmdb_id; 
			   }else{
					return "Error";
			   }

		   }
	   
}
