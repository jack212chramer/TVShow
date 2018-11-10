package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.PaginatorDao;
import com.dao.seriesDatabase.TVShowContainer;
import com.dao.userDatabase.UserJDBC;
import com.dao.userRatings.UserRatingJDBC;

@Controller
public class RatingController {
	
	private String response;
	
	@Autowired 
	private PaginatorDao dao;
	@Autowired
	private UserRatingJDBC userRatingDao;
	@Autowired
	private UserJDBC userJDBC;
	
	@RequestMapping(value="/setSerieStatus",method = RequestMethod.POST)
	public void setStatus(HttpSession session,
			@RequestParam(value="tmdb_id")String tmdb_id,
			@RequestParam(value="status")String status) {
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String username = auth.getName(); 
			String user_id=userJDBC.getUserId(username);
			userRatingDao.setShowStatus(tmdb_id, user_id,status);
	   }
	
	@RequestMapping(value="/setRating",method = RequestMethod.POST)
	public void setRating(HttpSession session,
			@RequestParam(value="tmdb_id")String tmdb_id,
			@RequestParam(value="rating")String rating) {
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String username = auth.getName(); 
			String user_id=userJDBC.getUserId(username);
			userRatingDao.updateRating(tmdb_id, user_id,rating);
	   }
	
	@RequestMapping(value="/getSerieStatus")
	@ResponseBody
	public String getStatus(HttpSession session,
			@RequestParam(value="tmdb_id")String tmdb_id) {
			String user_id=(String) session.getAttribute("user_id");
		 	response=userRatingDao.getShowStatus(tmdb_id, user_id);
		 	return response;
	   }
	
	@RequestMapping(value="/getRating")
	@ResponseBody
	public String getRating(HttpSession session,
			@RequestParam(value="tmdb_id")String tmdb_id) {
			String user_id=(String) session.getAttribute("user_id");
		 	response=userRatingDao.getRating(tmdb_id, user_id);
		 	return response;
	   }
	
	
	@RequestMapping(value="/myShows")
	public ModelAndView myShows(
			@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="user_id",required = true)String user_id,
			HttpSession session){
		 if(page == null) {
			   page = 1;
		    }
				
		TVShowContainer container = dao.getHighestRatedByUser(page, user_id);
		return new ModelAndView("myShows","container",container);
	}
}
