package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.PaginatorDao;
import com.dao.persons.RoleJDBC;
import com.dao.seriesDatabase.SqlValidator;
import com.dao.seriesDatabase.SeriesJDBC;
import com.dao.seriesDatabase.TVShow;
import com.dao.seriesDatabase.TVShowContainer;

@Controller
public class MainController {
	
	@Autowired
	private SeriesJDBC seriesJDBC;
		
	@Autowired 
	private PaginatorDao dao;
	
	@Autowired
	RoleJDBC roleJDBC;
	
	// HOMEPAGE
	
	@RequestMapping(value="/",method = RequestMethod.GET)public String nullToHomepage() {
	      return "redirect:/homepage";
	   }
	// TOP 5
   @RequestMapping(value="/homepage",method = RequestMethod.GET)
   public String homepage() {
      return "index/index";
   }
      
   // LOGIN WELCOME SCREEN
   @RequestMapping(value="/Home",method = RequestMethod.GET)public String Home() {
	      return "Home";
	   }

   //	ERROR PAGE
   @RequestMapping(value="/Error",method = RequestMethod.GET)public String Error() {
	      return "basic/Error";
	   }
   
   //	TOP 25 
   @RequestMapping(value="/top25",method = RequestMethod.GET)
   public ModelAndView top25() {
		   ArrayList<TVShow> top25series = seriesJDBC.getShows(25,1,0,true,"");
	      return new ModelAndView("top25","results",top25series);
	   }
      
   //	SEARCH
   @RequestMapping(value="/search",method = RequestMethod.GET)
   public ModelAndView search(
		   @RequestParam(value="search",required=true)String search,
		   @RequestParam(value="page",required=false)Integer page) {
	   
	   if(page == null) {
		   page = 1;
	    }
	   TVShowContainer container = null;
	   if(SqlValidator.checkInputLength(search)){
		   container=dao.getSearchTVShowContainerForPage(search, page);
		   //searchResults = seriesJDBC.searchByName(search,-1,0);
	   }
	      return new ModelAndView("searchResults","container",container);
	   }
   
   //	TV SHOW PAGE
   @RequestMapping(value="/serial",method = RequestMethod.GET)
   public ModelAndView serial(@RequestParam(value="tmdb_id")int id) {
	   	TVShow infoAboutShow=seriesJDBC.searchById(id);
	   	ModelAndView mav = new ModelAndView("serial","results",infoAboutShow);
	   	
	   	mav.addObject("Roles", roleJDBC.getRoles(id));
	       return mav;
	   }
      
   
   // ALL SERIES VIEW   
   @RequestMapping(value="/tvShows",method = RequestMethod.GET)
   public ModelAndView tvShows(
		   @RequestParam(value="sortBy", required=true)Integer sortBy,
		   @RequestParam(value="page", required=false)Integer page) {
	   if(page == null) {
		   page = 1;
	    }
	   TVShowContainer container = dao.getTVShowContainerForPage(page, sortBy);
	   
	   return new ModelAndView("tvShows","results",container);
	   }
   
   //	NEXT AIR DATE 
   @RequestMapping(value="/nextAirDate",method = RequestMethod.GET)
   public ModelAndView nextAirDate(
		   @RequestParam(value="page", required=false)Integer page) {
	   if(page == null) {
		   page = 1;
	    }
	   TVShowContainer container = dao.getPremieres(page, 8);
	      return new ModelAndView("nextAirDate","results",container);
	   }

  
    
}