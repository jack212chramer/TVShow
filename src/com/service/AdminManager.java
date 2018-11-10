package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.dao.userDatabase.Comment;
import com.dao.userDatabase.CommentJDBC;
import com.dao.userDatabase.User;
import com.dao.userDatabase.UserContainer;
import com.dao.userDatabase.UserJDBC;

@Component
public class AdminManager {
	@Autowired
	PersonJDBC personJDBC;	
	@Autowired
	UserJDBC userJDBC;
	@Autowired
	PaginatorDao paginatorDao;
	@Autowired
	RoleJDBC roleJDBC;
	@Autowired
	SeriesJDBC seriesJDBC;
	@Autowired
	CommentJDBC commentJDBC;
	
	private Integer page;
	private String str;
	
	public boolean isAdmin(HttpSession session){
		
		User user;
		try{
			String id =session.getAttribute("user_id").toString();
			user = userJDBC.getUser(id);
		}catch(Exception e){
			user = new User();
			user.setPrivileges(0);
		}
		if(user.getPrivileges()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public void changeParamsIfNull(){
		if(page == null) {
			   page = 1;
		    }
		   if(str == null) {
			   str = "";
		    }
	}

	public UserContainer getUserContainer(Integer page,String search,HttpSession session){
		this.page=page;
		this.str=search;
		changeParamsIfNull();
		if(isAdmin(session)){
			return paginatorDao.getUsers(this.page, str);
		}else{
			return null;
		}	
	}

	public String getAdminPanel(HttpSession session){
		if(isAdmin(session)){
			return "admin/adminPanel";
		}else{
			return "Error"; 
		}		
	}

	public NewsContainer getNewsContainer(Integer page,String search,HttpSession session){
		this.page=page;
		changeParamsIfNull();
		if(isAdmin(session)){
			return paginatorDao.getNewsContainerForPage(this.page);
		}else{
			return null;
		}	
	}

	public PersonContainer getPersonContainer(Integer page,String search,HttpSession session){
		this.page=page;
		this.str=search;
		changeParamsIfNull();
		if(isAdmin(session)){
			return paginatorDao.getPersons(this.page, str);
		}else{
			return null;
		}	
	}
	
	public ArrayList<Person> getPersonList(Integer page,String search,HttpSession session){
		if(isAdmin(session)){
			return personJDBC.getPersons(" ORDER BY name");
		}else{
			return null;
		}	
	}

	public void addRole(HttpSession session, Role role){
		
		if(isAdmin(session)){
			roleJDBC.addRole(role);
		}
	}

	public void deleteRole(HttpSession session, Role role){
		if(isAdmin(session)){
			roleJDBC.deleteRole(role);
		}
	}

	public void addPerson(String name,String image,HttpSession session){
		this.str=image;
		changeParamsIfNull();
		if(isAdmin(session)){
			Person person = new Person(0,name,str);
			personJDBC.addPerson(person);
		}
	}

	public void updatePerson(int id,String name,String image,HttpSession session){
		this.str=image;
		changeParamsIfNull();
		if(isAdmin(session)){
			Person person = new Person(id,name,str);
			personJDBC.updatePerson(person);
		}
	}

	public void deletePerson(int id,HttpSession session){
		
		if(isAdmin(session)){
			Person person = new Person();
			   person.setId(id);
			   personJDBC.deletePerson(person);
			   roleJDBC.deleteRolesOfPerson(person);
		}
	}

	public TVShowContainer getTVShowContainer(Integer page,String search,HttpSession session){
		this.page=page;
		this.str=search;
		changeParamsIfNull();
		if(isAdmin(session)){
			return paginatorDao.getSearchTVShowContainerForPage(str, this.page);
		}else{
			return null;
		}	
	}

	public void deleteShow(int id,HttpSession session){
		if(isAdmin(session)){
			TVShow show = new TVShow();
			show.setTmdb_id(id);
			seriesJDBC.deleteShow(show);
		}
	}
	
	public void updateShow(TVShow show,HttpSession session){
		if(isAdmin(session)){
			seriesJDBC.updateShow(show);
		}
	}

	public void addShow(TVShow show,HttpSession session){
		if(isAdmin(session)){
			seriesJDBC.createShow(show);
		}
	}

	public String getNewShowPanel(HttpSession session){
		if(isAdmin(session)){
			return "admin/newShow";
		}else{
			return "Error"; 
		}	
	}
	
	public TVShow getShow(int id, HttpSession session){
		if(isAdmin(session)){
			return seriesJDBC.searchById(id);
		}else{
			return null;
		}
	}
	
	public void banUser(String user_id,Integer dayCount,HttpSession session){
		if(isAdmin(session)){
			User user = userJDBC.getUser(user_id);
			user.setBannedForDays(dayCount);
			userJDBC.updateUser(user);
		}
	}
	
	public void increaseAdminPrivileges(String user_id,HttpSession session){
		if(isAdmin(session)){
			User user = userJDBC.getUser(user_id);
			int privileges = user.getPrivileges();
			if(privileges==0){
				user.setPrivileges(privileges+1);
				userJDBC.updateUser(user);
			}
		}
	}
	
	public void decreaseAdminPrivileges(String user_id,HttpSession session){
		if(isAdmin(session)){
			User user = userJDBC.getUser(user_id);
			int privileges = user.getPrivileges();
			if(privileges>0){
				user.setPrivileges(privileges-1);
				userJDBC.updateUser(user);
			}
		}
	}
	
	public void deleteComment(Comment comment, HttpSession session){
		int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		if(isAdmin(session)||user_id==comment.getUser_id()){
			commentJDBC.deleteComment(comment);
		}
	}
}
