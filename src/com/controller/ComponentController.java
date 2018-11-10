package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.seriesDatabase.Season;
import com.dao.seriesDatabase.SeasonsJDBC;
import com.dao.seriesDatabase.SeriesJDBC;
import com.dao.seriesDatabase.TVShow;
import com.dao.seriesDatabase.TVShowContainer;
import com.dao.userDatabase.Comment;
import com.dao.userDatabase.CommentContainer;
import com.dao.userDatabase.CommentJDBC;
import com.dao.userRatings.UserRatingJDBC;

@Controller
public class ComponentController {
	@Autowired
	private SeriesJDBC seriesJDBC;
	@Autowired
	private UserRatingJDBC userRatingDao;
	@Autowired
	private CommentJDBC commentJDBC;
	@Autowired
	private SeasonsJDBC seasonsJDBC;

	   @RequestMapping(value="/header",method = RequestMethod.GET)
	   public String header() {
		      return "basic/header";
		   }

	   @RequestMapping(value="/footer",method = RequestMethod.GET)
   		public String footer() {
	      return "basic/footer";
	   		}
	   
	   @RequestMapping(value="/joinUs",method = RequestMethod.GET)
	   public String joinUs() {
		      return "index/joinUs";
		   }
	   @RequestMapping(value="/sayHello",method = RequestMethod.GET)
	   public ModelAndView sayHello() {
		   ArrayList<TVShow> top5series = seriesJDBC.getShows(5,0,0,true,"");
		      return new ModelAndView("index/sayHello","top5",top5series);
		   }
		@RequestMapping(value="/getHighestRated")
		public ModelAndView getHighestRated(HttpSession session){
			String user_id=(String) session.getAttribute("user_id");
			ArrayList<TVShow> listOfHighestRatedShows = userRatingDao.getHighestRated(user_id,5,0);
			//Create POJO
			TVShowContainer tVShowArrayList = new TVShowContainer(listOfHighestRatedShows);
			return new ModelAndView("index/highestRated","container",tVShowArrayList);
		}
		@RequestMapping(value="/getRecentlyWatched")
		public ModelAndView getRecentlyWatched(HttpSession session){
			String user_id=(String) session.getAttribute("user_id");
			ArrayList<TVShow> listOfRecentlyWatchedShows = userRatingDao.getRecentlyWatched(user_id);
			//Create POJO
			TVShowContainer tVShowArrayList = new TVShowContainer(listOfRecentlyWatchedShows);
			return new ModelAndView("index/recentlyWatched","container",tVShowArrayList);
		}
		
		@RequestMapping(value="/comments")
		public ModelAndView comments(
				@RequestParam(value="id")int id,
				@RequestParam(value="type")int type){
			
			ArrayList<Comment> list = null;
			if(type==0){
				list = commentJDBC.getCommentsForShow(id);
			}else if(type==1){
				list = commentJDBC.getCommentsForNews(id);
			}
			CommentContainer container = new CommentContainer(list);
			container.setId(id);
			container.setType(type);
			return new ModelAndView("basic/comments","container",container);
		}
		
		@RequestMapping(value="/seasons")
		public ModelAndView seasons(
				@RequestParam(value="tmdb_id")int id){
			
			ArrayList<Season> seasons = seasonsJDBC.getSeasons(id);
			return new ModelAndView("seasons","seasons",seasons);
		}
}
