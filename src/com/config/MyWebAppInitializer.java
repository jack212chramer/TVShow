package com.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;

import com.service.scheduling.RatingUpdater;
import com.service.scheduling.Scheduler;


@SuppressWarnings("unused")
public class MyWebAppInitializer implements WebApplicationInitializer {
	
	/**
	 * 	CREATING NECESSARY INSTANCES ON STARTUP
	 */
	
	@Autowired
	private Scheduler scheduler;
	
	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		
		

	}

}
