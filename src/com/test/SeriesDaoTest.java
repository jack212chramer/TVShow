package com.test;

import org.junit.Test;

import com.dao.seriesDatabase.SeriesJDBC;

public class SeriesDaoTest {
	
	@Test
	public void test(){
		SeriesJDBC seriesJDBC = new SeriesJDBC();
		System.out.println(seriesJDBC.searchById(1668));
		System.out.println(seriesJDBC.searchById(1418));
	}

}
