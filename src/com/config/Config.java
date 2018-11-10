package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.dao.PaginatorDao;
import com.dao.news.NewsJDBC;
import com.dao.persons.PersonJDBC;
import com.dao.persons.RoleJDBC;
import com.dao.seriesDatabase.SeasonsJDBC;
import com.dao.seriesDatabase.SeriesJDBC;
import com.dao.userDatabase.UserRegistering;
import com.dao.userDatabase.CommentJDBC;
import com.dao.userDatabase.UserJDBC;
import com.dao.userRatings.UserRatingJDBC;
import com.service.AdminManager;
import com.service.DateAndTimeService;
import com.service.LoginOrRegisterService;
import com.service.Paginator;
import com.service.PaginatorConfig;
import com.service.scheduling.BanUpdater;
import com.service.scheduling.RatingUpdater;
import com.service.scheduling.Scheduler;

@Configuration
@EnableScheduling
public class Config {
			
	@Bean
	public UserJDBC userJDBC() {
	    return new UserJDBC();
	}
	
	@Bean
	public UserRegistering registrationValidation() {
	    return new UserRegistering();
	}
	
	@Bean
	public SeriesJDBC seriesJDBC(){
		return new SeriesJDBC();
	}
	
	@Bean
	public UserRatingJDBC userRatingDao(){
		return new UserRatingJDBC();
	}
	
	@Bean
	public RatingUpdater schedulingRatingUpdates(){
		return new RatingUpdater();
	}
	
	@Bean
	public LoginOrRegisterService loginOrRegister(){
		return new LoginOrRegisterService();
	}
	
	@Bean
	public NewsJDBC newsJDBC(){
		return new NewsJDBC();
	}
	
	@Bean
	public Paginator paginator(){
		return new Paginator();
	}
	
	@Bean
	public PaginatorConfig paginatorConfig(){
		return new PaginatorConfig();
	}
	
	@Bean
	public PaginatorDao dao(){
		return new PaginatorDao();
	}
	
	@Bean
	public DateAndTimeService dateAndTimeService(){
		return new DateAndTimeService();
	}
	
	@Bean
	public Scheduler scheduler(){
		return new Scheduler();
	}
	
	@Bean
	public BanUpdater banUpdater(){
		return new BanUpdater();
	}
	
	@Bean
	public RoleJDBC roleJDBC(){
		return new RoleJDBC();
	}
	
	@Bean
	public PersonJDBC personJDBC(){
		return new PersonJDBC();
	}
	
	@Bean
	public AdminManager adminManager(){
		return new AdminManager();
	}
	
	@Bean
	public CommentJDBC commentJDBC(){
		return new CommentJDBC();
	}
	
	@Bean
	public SeasonsJDBC seasonsJDBC(){
		return new SeasonsJDBC();
	}
	
}