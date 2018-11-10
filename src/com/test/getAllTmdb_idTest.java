package com.test;

import java.util.ArrayList;

import org.junit.Test;

import com.dao.seriesDatabase.SeriesJDBC;

public class getAllTmdb_idTest {

	@Test
	public void test() {
		SeriesJDBC seriesJDBC = new SeriesJDBC();
		ArrayList<Integer> test =seriesJDBC.getAllTmdb_id();
		for(int i=0;i<test.size();i++){
			System.out.println(test.get(i));
		}
		System.out.println("Size: "+test.size());
	}

}
