package com.dao.persons;

import java.util.ArrayList;

public interface PersonDao {

	public void addPerson(Person person);
	
	public void updatePerson(Person person);
	
	public void deletePerson(Person person);
	
	public ArrayList<Person> getPersons(String sqlCondition);
}
