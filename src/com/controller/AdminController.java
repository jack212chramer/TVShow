package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.PaginatorDao;
import com.dao.news.NewsContainer;
import com.dao.persons.Person;
import com.dao.persons.PersonContainer;
import com.dao.persons.PersonJDBC;
import com.dao.persons.Role;
import com.dao.persons.RoleJDBC;
import com.dao.seriesDatabase.SeriesJDBC;
import com.dao.seriesDatabase.TVShow;
import com.dao.seriesDatabase.TVShowContainer;
import com.dao.userDatabase.UserContainer;
import com.dao.userDatabase.UserJDBC;
import com.service.AdminManager;

@Controller
public class AdminController {
	
	@Autowired
	AdminManager adminManager;
	@Autowired
	UserJDBC userJDBC;
	@Autowired
	PaginatorDao paginatorDao;
	@Autowired
	SeriesJDBC seriesJDBC;
	@Autowired
	PersonJDBC personJDBC;
	@Autowired
	RoleJDBC roleJDBC;
	
	
	 //	ADMIN PANEL
	   @RequestMapping(value="/adminPanel")
	   public String adminPanel(HttpSession session) {
		   return adminManager.getAdminPanel(session);
		   }
	   
	   //ADMIN USER PANEL
	   @RequestMapping(value="/userPanel")
	   public ModelAndView userPanel(
			   @RequestParam(value="page",required = false)Integer page,
			   @RequestParam(value="search",required = false)String search,
			   HttpSession session) {

		    UserContainer container = adminManager.getUserContainer(page, search, session);
		  return new ModelAndView("admin/userPanel","container",container);		   	
		   }
	   
	   //ADMIN NEWS PANEL
	   @RequestMapping(value="/newsPanel")
	   public ModelAndView newsPanel(
			   @RequestParam(value="page",required = false)Integer page,
			   HttpSession session) {
		  
		   NewsContainer container = adminManager.getNewsContainer(page, "", session);
		   return new ModelAndView("admin/newsPanel","container",container);
		   }
	   
	 //ADMIN ROLE EDITION PANEL
	   @RequestMapping(value="/rolePanel")
	   public ModelAndView rolePanel(
			   @RequestParam(value="page",required = false)Integer page,
			   @RequestParam(value="search",required = false)String search,
			   HttpSession session) {
		   
		   PersonContainer container = adminManager.getPersonContainer(page, "", session);
		   return new ModelAndView("admin/rolePanel","container",container);
		   }
	   //GET ADD ROLE COMPONENT
	   @RequestMapping(value="/getAddRole")
	   public ModelAndView getAddRole(
			   HttpSession session) {

		   ArrayList<Person> list = adminManager.getPersonList(0, "", session);
			return new ModelAndView("admin/addRole","personList",list);    	
		   }
	   
	 //ADD ROLE
	   @RequestMapping(value="/addRole")
	   public ModelAndView addRole(
			   @RequestParam(value="tmdb_id")Integer show_id,
			   @RequestParam(value="person_id")Integer person_id,
			   @RequestParam(value="character_name")String character_name,
			   HttpSession session) {
		   
		   Role role = new Role();
		   role.setCharacter_name(character_name);
		   role.setPerson_id(person_id);
		   role.setShow_id(show_id);
		   role.setRole(0);
		   
		   adminManager.addRole(session, role);
		   return new ModelAndView("redirect:/homepage"); 
		   }
	   
	   //NEW SHOW PANEL
	   @RequestMapping(value="/newShow")
	   public String newShow(HttpSession session) {

		   return adminManager.getNewShowPanel(session);
		   }
	   //ADD SHOW PANEL
	   @RequestMapping(value="/editShow")
	   public ModelAndView editShow(
			   @RequestParam(value="tmdb_id")Integer id,
			   HttpSession session) {

		   TVShow show = adminManager.getShow(id, session);		   
		   return new ModelAndView("admin/editShow","results",show); 
		   }
	   
	   //ADD SHOW
	   @RequestMapping(value="/addShow",method = RequestMethod.POST)
	   public String addShow(
			   HttpSession session,
			   @RequestParam(value="name")String name,
			   @RequestParam(value="original_name")String original_name,
			   @RequestParam(value="first_air_date")String first_air_date,
			   @RequestParam(value="last_air_date")String last_air_date,
			   @RequestParam(value="original_language")String original_language,
			   @RequestParam(value="number_of_seasons")int number_of_seasons,
			   @RequestParam(value="number_of_episodes")int number_of_episodes,
	   			@RequestParam(value="homepage")String homepage,
				@RequestParam(value="overview")String overview,
				@RequestParam(value="poster_path")String poster_path,
				@RequestParam(value="backdrop_path")String backdrop_path){
						   
		  TVShow show = new TVShow();
				  show.setName(name);
				  show.setOriginal_name(original_name);
				  show.setFirst_air_date(first_air_date);
				  show.setLast_air_date(last_air_date);
				  show.setOriginal_language(original_language);
				  show.setNumber_of_seasons(number_of_seasons);
				  show.setNumber_of_episodes(number_of_episodes);
				  show.setHomepage(homepage);
				  show.setOverview(overview);
				  show.setPoster_path(poster_path);
				  show.setBackdrop_path(backdrop_path);
				  	
	    	  
		   adminManager.addShow(show,session);
		   return "redirect:/showPanel";
	   }
	   
	   //UPDATE SHOW
	   @RequestMapping(value="/updateShow",method = RequestMethod.POST)
	   public String updateShow(
			   HttpSession session,
			   @RequestParam(value="id",required=true)int id,
			   @RequestParam(value="name")String name,
			   @RequestParam(value="original_name")String original_name,
			   @RequestParam(value="first_air_date")String first_air_date,
			   @RequestParam(value="last_air_date")String last_air_date,
			   @RequestParam(value="original_language")String original_language,
			   @RequestParam(value="number_of_seasons")int number_of_seasons,
			   @RequestParam(value="number_of_episodes")int number_of_episodes,
	   			@RequestParam(value="homepage")String homepage,
				@RequestParam(value="overview")String overview,
				@RequestParam(value="poster_path")String poster_path,
				@RequestParam(value="backdrop_path")String backdrop_path){
						   
		  TVShow show = new TVShow();
		  		show.setTmdb_id(id);
				  show.setName(name);
				  show.setOriginal_name(original_name);
				  show.setFirst_air_date(first_air_date);
				  show.setLast_air_date(last_air_date);
				  show.setOriginal_language(original_language);
				  show.setNumber_of_seasons(number_of_seasons);
				  show.setNumber_of_episodes(number_of_episodes);
				  show.setHomepage(homepage);
				  show.setOverview(overview);
				  show.setPoster_path(poster_path);
				  show.setBackdrop_path(backdrop_path);
				  	
	    	  
		   adminManager.updateShow(show,session);
		   return "redirect:/showPanel";
	   }
	   
		 //DELETE ROLE
	   @RequestMapping(value="/deleteRole")
	   public ModelAndView deleteRole(
			   @RequestParam(value="role_id")Integer role_id,
			   HttpSession session) {

		   Role role = new Role();
		   role.setRoleId(role_id);
		   adminManager.deleteRole(session, role);
		   return new ModelAndView("redirect:/homepage"); 
		   }
	   
	   //ADMIN PERSON EDITION PANEL
	   @RequestMapping(value="/personPanel")
	   public ModelAndView personPanel(
			   @RequestParam(value="page",required = false)Integer page,
			   @RequestParam(value="search",required = false)String search,
			   HttpSession session) {
		   
		   PersonContainer container = adminManager.getPersonContainer(page, search, session);
		   return new ModelAndView("admin/personPanel","container",container); 		   	
		   }
	   
	   //	ADD PERSON
	   @RequestMapping(value="/addPerson")
	   public ModelAndView addPerson(
			   @RequestParam(value="name",required = true)String name,
			   @RequestParam(value="image",required = false)String image,
			   HttpSession session) {
		   
		   adminManager.addPerson(name, image, session);
		   return new ModelAndView("redirect:/personPanel"); 
		   }
	   
	   
	   //	UPDATE PERSON
	   @RequestMapping(value="/updatePerson")
	   public ModelAndView updatePerson(
			   @RequestParam(value="person_id",required = true)int id,
			   @RequestParam(value="name",required = true)String name,
			   @RequestParam(value="image",required = false)String image,
			   HttpSession session) {
		   
		   adminManager.updatePerson(id,name, image, session);
		   return new ModelAndView("redirect:/personPanel"); 
		   }
	   
//		DELETE PERSON
	   @RequestMapping(value="/deletePerson")
	   public ModelAndView deletePerson(
			   @RequestParam(value="person_id",required = true)int id,
			   HttpSession session) {
		   
		   adminManager.deletePerson(id, session);
		   return new ModelAndView("redirect:/homepage"); 		   	
		   }
	   
	 //ADMIN SHOW EDITION PANEL
	   @RequestMapping(value="/showPanel")
	   public ModelAndView showPanel(
			   @RequestParam(value="page",required = false)Integer page,
			   @RequestParam(value="search",required = false)String search,
			   HttpSession session) {
		   
			   TVShowContainer container = adminManager.getTVShowContainer(page, search, session);
			   return new ModelAndView("admin/showPanel","container",container); 
		   }
	   
	   //	DELETE SHOW
	   @RequestMapping(value="/deleteShow")
	   public ModelAndView deleteShow(
			   @RequestParam(value="tmdb_id",required = true)int id,
			   HttpSession session) {
		  
		   adminManager.deleteShow(id, session);
		   return new ModelAndView("admin/showPanel"); 
		   }
	   
	   //	BAN USER
	   @RequestMapping(value="/banUser")
	   public String banUser(
			   HttpSession session,
			   @RequestParam(value="user_id")String user_id,
			   @RequestParam(value="dayCount")Integer dayCount){
		   
		  adminManager.banUser(user_id, dayCount, session);
		  return "redirect:/userPanel";
	   }
	   
	   //	CHANGE ADMIN PRIVILEGES
	   @RequestMapping(value="/increaseAdminPrivileges")
	   public String increaseAdminPrivileges(
			   HttpSession session,
			   @RequestParam(value="user_id")String user_id){
		   
		   adminManager.increaseAdminPrivileges(user_id, session);
		   return "redirect:/userPanel";
	   }
	   
	   @RequestMapping(value="/decreaseAdminPrivileges")
	   public String decreaseAdminPrivileges(
			   HttpSession session,
			   @RequestParam(value="user_id")String user_id){

		   adminManager.decreaseAdminPrivileges(user_id, session);
			return "redirect:/userPanel";
	   }
}
