package com.test;

import org.junit.Test;
import com.service.DateAndTimeService;

public class getCurrentDateAndTimeTest {
	
	@Test
	public void test(){
		DateAndTimeService dateAndTimeService = new DateAndTimeService();
		String date=dateAndTimeService.getCurrentDateAndTime();
		System.out.println(date);
	}
}
