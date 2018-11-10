package com.dao.persons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.config.ConfigJDBC;

public class PersonJDBC extends ConfigJDBC implements PersonDao{

	@Override
	public void addPerson(Person person) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("INSERT INTO persons(name,image) VALUES(?,?);");
		      stmt.setString(1, person.getName());
		      stmt.setString(2, person.getImage());
		      stmt.executeUpdate();
		      c.commit();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
	}

	@Override
	public void updatePerson(Person person) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("UPDATE persons SET name=?,image=? WHERE person_id =?;");
		      stmt.setString(1, person.getName());
		      stmt.setString(2, person.getImage());
		      stmt.setInt(3, person.getId());
		      stmt.executeUpdate();
		      c.commit();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
	}

	@Override
	public void deletePerson(Person person) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("DELETE FROM persons WHERE person_id=?;");
		      stmt.setInt(1, person.getId());
		      stmt.executeUpdate();
		      c.commit();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
	}

	@Override
	public ArrayList<Person> getPersons(String sqlCondition) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   ResultSet rs = null;
		   ArrayList<Person> list = new ArrayList<>();
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("SELECT * FROM persons "+sqlCondition+";");

		      rs = stmt.executeQuery();
		      while(rs.next()){
		    	  Person person = new Person(
		    			  rs.getInt("person_id"),
		    			  rs.getString("name"),
		    			  rs.getString("image"));
		    	  list.add(person);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		   return list;
	}
		
}
