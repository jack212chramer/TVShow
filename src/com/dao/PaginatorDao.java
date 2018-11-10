package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.news.NewsContainer;
import com.dao.persons.PersonContainer;
import com.dao.seriesDatabase.TVShowContainer;
import com.dao.userDatabase.UserContainer;
import com.service.Paginator;

@Repository
public class PaginatorDao {
	
	@Autowired
	Paginator paginator;

	public TVShowContainer getTVShowContainerForPage(int page,int sortBy){
		
		return (TVShowContainer) paginator.getElementsForPage(page, "tvShows", sortBy, "","");	
	}
	
	public NewsContainer getNewsContainerForPage(int page){
		return (NewsContainer) paginator.getElementsForPage(page, "newsList", 0, "","");
	}
	
	public TVShowContainer getSearchTVShowContainerForPage(String userInput,int page){
		
		return (TVShowContainer) paginator.getElementsForPage(page, "searchResults", 0, userInput,"");	
	}
	
	public TVShowContainer getPremieres(int page,int sortBy){
		
		return (TVShowContainer) paginator.getElementsForPage(page, "tvShows", sortBy,"", "WHERE next_episode_to_air !='null' ");	
	}
	
	public TVShowContainer getHighestRatedByUser(int page,String user_id){
		return (TVShowContainer) paginator.getElementsForPage(page, "myShows", 0, "", user_id);	
	}
	
	public UserContainer getUsers(int page, String search){
		return (UserContainer) paginator.getElementsForPage(page, "users", 0, search, "");
	}
	
	public PersonContainer getPersons(int page, String search){
		return (PersonContainer) paginator.getElementsForPage(page, "persons", 0, search, "");
	}
}
