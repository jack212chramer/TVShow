package com.service;

import org.springframework.stereotype.Component;

@Component
public class PaginatorConfig {
	
	private int numberOfNewsPerPage=10;
	private int numberOfTvShowsPerPage=10;
	private int numberOfMyShowsPerPage=18;
	private int numberOfUsersPerPage=30;
	private int numberOfPersonsPerPage=30;
	
	public int getNumberOfNewsPerPage() {
		return numberOfNewsPerPage;
	}
	public int getNumberOfTvShowsPerPage() {
		return numberOfTvShowsPerPage;
	}
	public int getNumberOfMyShowsPerPage() {
		return numberOfMyShowsPerPage;
	}
	public int getNumberOfUsersPerPage() {
		return numberOfUsersPerPage;
	}
	public int getNumberOfPersonsPerPage() {
		return numberOfPersonsPerPage;
	}
	
	
}
