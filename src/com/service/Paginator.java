package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.news.News;
import com.dao.news.NewsContainer;
import com.dao.news.NewsJDBC;
import com.dao.persons.Person;
import com.dao.persons.PersonContainer;
import com.dao.persons.PersonJDBC;
import com.dao.seriesDatabase.SqlValidator;
import com.dao.seriesDatabase.SeriesJDBC;
import com.dao.seriesDatabase.TVShow;
import com.dao.seriesDatabase.TVShowContainer;
import com.dao.userDatabase.User;
import com.dao.userDatabase.UserContainer;
import com.dao.userDatabase.UserJDBC;
import com.dao.userRatings.UserRatingJDBC;

@Service
public class Paginator {
	@Autowired
	PaginatorConfig paginatorConfig;
	@Autowired
	NewsJDBC newsJDBC;
	@Autowired
	SeriesJDBC seriesJDBC;
	@Autowired
	private UserRatingJDBC userRatingJDBC;
	@Autowired
	private UserJDBC userJDBC;
	@Autowired
	private PersonJDBC personJDBC;
	
	private String elementType;
	private int allElementsCount;
	private int numberOfElementsPerPage;
	private int offset;
	private Object container;
	private int activePage;
	private int sortBy;
	private String userInput;
	private String sqlCondition;
	
		
	public Object getElementsForPage(int activePage,String elementType,int sortBy,String userInput,String sqlCondition){
		this.activePage=activePage-1;
		this.elementType=elementType;
		this.sqlCondition=sqlCondition;
		this.userInput=SqlValidator.SQLInjectionEscape(userInput, true);
		this.sortBy=sortBy;
		setElementsContainer();
		return container ;
	}
		
	public void setElementsContainer(){
		switch (elementType){
			case "tvShows":  elementType = "tvShows";
				this.numberOfElementsPerPage=paginatorConfig.getNumberOfTvShowsPerPage();
				allElementsCount=seriesJDBC.countAllSeries(sqlCondition);
				container=getTVShowContainer();
	        break;
			case "newsList":  elementType = "newsList";
				this.numberOfElementsPerPage=paginatorConfig.getNumberOfNewsPerPage();
				allElementsCount=newsJDBC.getNewsCount();
				container=getNewsContainer();
			break;
			case "searchResults":  elementType = "searchResults";
				this.numberOfElementsPerPage=paginatorConfig.getNumberOfTvShowsPerPage();
				allElementsCount=seriesJDBC.searchByNameCount(userInput);
				container=getSearchResultsContainer();
			break;
			case "myShows":  elementType = "myShows";
				this.numberOfElementsPerPage=paginatorConfig.getNumberOfMyShowsPerPage();
				container=getMyShows();
			break;
			case "users":  elementType = "users";
				this.numberOfElementsPerPage=paginatorConfig.getNumberOfUsersPerPage();
				container=getUsers();
			break;
			case "persons":  elementType = "persons";
				this.numberOfElementsPerPage=paginatorConfig.getNumberOfPersonsPerPage();
				container=getPersons();
			break;
		}
		
	}
	
	public NewsContainer getNewsContainer(){
		this.offset = activePage*numberOfElementsPerPage;
		ArrayList<News> newsList=newsJDBC.getNewsList(numberOfElementsPerPage,offset);
		int pages=(int) Math.ceil(allElementsCount / (double)numberOfElementsPerPage);
		NewsContainer container = new NewsContainer(newsList);
		container.setPages(pages);
		container.setPage(activePage);
		return container;
	}
		
	public TVShowContainer getTVShowContainer(){
		this.offset = activePage*numberOfElementsPerPage;
		boolean desc=true;
		if(sortBy==2||sortBy==3||sortBy==8){
			desc=false;
		}
		ArrayList<TVShow> tvShowList=seriesJDBC.getShows(numberOfElementsPerPage, sortBy, offset, desc,sqlCondition);
		int pages=(int) Math.ceil(allElementsCount / (double)numberOfElementsPerPage);
		TVShowContainer container = new TVShowContainer(tvShowList);
		container.setPages(pages);
		container.setPage(activePage);
		container.setSortBy(sortBy);
		return container;
	}
	
	public TVShowContainer getSearchResultsContainer(){
		this.offset = activePage*numberOfElementsPerPage;

		ArrayList<TVShow> tvShowList=seriesJDBC.searchByName(userInput,numberOfElementsPerPage, offset);
		int pages=(int) Math.ceil(allElementsCount / (double)numberOfElementsPerPage);
		TVShowContainer container = new TVShowContainer(tvShowList);
		container.setPages(pages);
		container.setPage(activePage);
		container.setSearch(userInput);
		container.setAllShowsInDatabase(allElementsCount);
		return container;
	}
	
	public TVShowContainer getMyShows(){
		this.offset = activePage*numberOfElementsPerPage;
		
		allElementsCount=userRatingJDBC.getHighestRated(sqlCondition, -1, 0).size();
		ArrayList<TVShow> tvShowList=userRatingJDBC.getHighestRated(sqlCondition, numberOfElementsPerPage, offset);
		int pages=(int) Math.ceil(allElementsCount / (double)numberOfElementsPerPage);
		TVShowContainer container = new TVShowContainer(tvShowList);
		container.setPages(pages);
		container.setPage(activePage);
		container.setSearch(userInput);
		container.setAllShowsInDatabase(allElementsCount);
		container.setUsername(userJDBC.getUser(sqlCondition).getUsername());
		container.setUser_id(sqlCondition);
		return container;		
	}
	
	public UserContainer getUsers(){
		this.offset = activePage*numberOfElementsPerPage;
		sqlCondition = " WHERE login LIKE '%"+userInput+"%' OR user_id='"+userInput+"' ";
		allElementsCount = userJDBC.getUsers(sqlCondition).size();
		String sqlCondition2 = " LIMIT "+numberOfElementsPerPage+" OFFSET "+offset;
		ArrayList<User> listOfUsers = userJDBC.getUsers(sqlCondition+sqlCondition2);
		int pages=(int) Math.ceil(allElementsCount / (double)numberOfElementsPerPage);
		UserContainer container = new UserContainer(listOfUsers);
		container.setPage(activePage);
		container.setPages(pages);
		container.setSearch(userInput);
		return container;
	}
	
	public PersonContainer getPersons(){
		this.offset = activePage*numberOfElementsPerPage;
		sqlCondition = " WHERE name LIKE '%"+userInput+"%' OR person_id='"+userInput+"' ";
		allElementsCount = personJDBC.getPersons(sqlCondition).size();
		String sqlCondition2 = "ORDER BY name LIMIT "+numberOfElementsPerPage+" OFFSET "+offset;
		ArrayList<Person> listOfUsers = personJDBC.getPersons(sqlCondition+sqlCondition2);
		int pages=(int) Math.ceil(allElementsCount / (double)numberOfElementsPerPage);
		PersonContainer container = new PersonContainer(listOfUsers);
		container.setPage(activePage);
		container.setPages(pages);
		container.setSearch(userInput);
		return container;
	}
	
	
}
