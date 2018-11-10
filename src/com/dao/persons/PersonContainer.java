package com.dao.persons;

import java.util.ArrayList;

import com.dao.PaginatorContainer;

public class PersonContainer extends PaginatorContainer{

	private ArrayList<Person> list;

	/**
	 * @param list
	 */
	public PersonContainer(ArrayList<Person> list) {
		super();
		this.list = list;
	}

	public ArrayList<Person> getList() {
		return list;
	}

	public void setList(ArrayList<Person> list) {
		this.list = list;
	}
	
	
}
