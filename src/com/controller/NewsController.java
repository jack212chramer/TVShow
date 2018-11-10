package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.PaginatorDao;
import com.dao.news.News;
import com.dao.news.NewsContainer;
import com.dao.news.NewsJDBC;
import com.dao.userDatabase.User;
import com.dao.userDatabase.UserJDBC;

@Controller
public class NewsController {
	
	@Autowired 
	private PaginatorDao dao;
	
	@Autowired
	private NewsJDBC newsJDBC;
	
	@Autowired
	private UserJDBC userJDBC;

	  //	NEWS LIST PAGE
	   @RequestMapping(value="/newsList",method = RequestMethod.GET)
	   public ModelAndView newsList(
			   @RequestParam(value="page",required=false)Integer page) {
		   if(page == null) {
			   page = 1;
		    }
		  
		   NewsContainer newsContainer = dao.getNewsContainerForPage(page);
		   
		      return new ModelAndView("news/newsList","results",newsContainer);
		   }
	   
//		NEWS PAGE
	  @RequestMapping(value="/news",method = RequestMethod.GET)
	  public ModelAndView news(
			  @RequestParam(value="news_id")int id) {
		   	News news = newsJDBC.getNewsWithId(id);
		      return new ModelAndView("news/news","news",news);
		   }
	  
//		NEWS CREATION PAGE
	@RequestMapping(value="/createNews",method = RequestMethod.GET)
	public String createNews(
			HttpSession session) {
		
		String adminId = session.getAttribute("user_id").toString();
		User admin = userJDBC.getUser(adminId);
		if(admin.getPrivileges()>0){
			 return "news/createNews";
		}else{
			 return "Error";
		}    
	}

	//NEWS CREATION
	@RequestMapping(value="/addNews",method = RequestMethod.POST)
	public String addNews(
			HttpSession session,
			@RequestParam(value="title")String title,
			@RequestParam(value="img_source")String img_source,
			@RequestParam(value="img_desc")String img_desc,
			@RequestParam(value="article_body",required=false)String article_body,
			@RequestParam(value="user_id")String user_id,
			@RequestParam(value="user")String user) {
		
		int author_id = Integer.parseInt(user_id);
		User admin = userJDBC.getUser(user_id);
	
		if(admin.getPrivileges()>0){
			News news = new News(0,title,img_source,article_body,author_id,user,img_desc,"");
			newsJDBC.insertNews(news);	
			return "redirect:/newsList";
		}else{
			return "Error";
		}
		
	   }

	//NEWS EDIT
	@RequestMapping(value="/newsEdit",method = RequestMethod.POST)
	public ModelAndView newsEdit(
			HttpSession session,
			@RequestParam(value="news_id")int id) {
		String adminID = session.getAttribute("user_id").toString();
		User admin = userJDBC.getUser(adminID);
		if(admin.getPrivileges()>0){
		News news = newsJDBC.getNewsWithId(id);
			return new ModelAndView("news/newsEdit","news",news);
		}else{
			return new ModelAndView( "Error");
		}
	}

	//NEWS UPDATE
	@RequestMapping(value="/updateNews",method = RequestMethod.POST)
	public String updateNews(
			HttpSession session,
			@RequestParam(value="news_id")int id,@RequestParam(value="title")String title,
			@RequestParam(value="img_source")String img_source,
			@RequestParam(value="img_desc")String img_desc,
			@RequestParam(value="article_body",required=false)String article_body,
			@RequestParam(value="user_id")int user_id,
			@RequestParam(value="user")String user) {
		
		String adminID = session.getAttribute("user_id").toString();
		User admin = userJDBC.getUser(adminID);
		if(admin.getPrivileges()>0){
			News news = new News(id,title,img_source,article_body,user_id,user,img_desc,"");
			newsJDBC.updateNews(id, news);
			return "redirect:/news?news_id="+id;
		}else{
			return "Error";
		}
	
	}

	//NEWS DELETE
	@RequestMapping(value="/newsDelete",method = RequestMethod.POST)
	public String newsDelete(
			HttpSession session,
			@RequestParam(value="news_id")int id) {
		
		String adminID = session.getAttribute("user_id").toString();
		User admin = userJDBC.getUser(adminID);
		if(admin.getPrivileges()>0){
			newsJDBC.deleteNews(id);
			return "redirect:/newsList";
		}else{
			return "Error";
		}
		
	    
	 }
}
