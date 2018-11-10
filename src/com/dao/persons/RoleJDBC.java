package com.dao.persons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.config.ConfigJDBC;

public class RoleJDBC extends ConfigJDBC implements RoleDao{

	@Override
	public Role getRole(int person_id, int show_id) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   Role role = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("select persons.name,persons.image,roles.* FROM persons  INNER JOIN roles ON persons.person_id = roles.person_id"
			      		+ "WHERE person_id=? AND show_id=?;");
		      stmt.setInt(1, person_id);
		      stmt.setInt(2, show_id);
		      ResultSet rs = stmt.executeQuery();
		      role = new Role(
		    		  person_id,
		    		  rs.getString("name"),
		    		  rs.getInt("role"),
		    		  show_id,
		    		  rs.getString("character_name"),
		    		  rs.getString("image")
		    		  );
		      role.setRoleId(rs.getInt("role_id"));
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return role;
	}

	@Override
	public ArrayList<Role> getRolesPlayedByPerson(int person_id) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   ArrayList<Role> list = new ArrayList<>();
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("select persons.name,roles.* FROM persons  INNER JOIN roles ON persons.person_id = roles.person_id"
			      		+ "WHERE person_id=?;");
		      stmt.setInt(1, person_id);
		      ResultSet rs = stmt.executeQuery();
		      while(rs.next()){
		    	  Role role = new Role(
			    		  person_id,
			    		  rs.getString("name"),
			    		  rs.getInt("role"),
			    		  rs.getInt("show_id"),
			    		  rs.getString("character_name"),
			    		  rs.getString("image")
			    		  );
		    	  role.setRoleId(rs.getInt("role_id"));
		    	  list.add(role);
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

	@Override
	public ArrayList<Role> getRoles(int show_id) {
			Connection c = null;
		   PreparedStatement stmt = null;
		   ArrayList<Role> list = new ArrayList<>();
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("select persons.*,roles.* FROM persons  INNER JOIN roles ON persons.person_id = roles.person_id "
			      		+ "WHERE show_id=?;");
		      stmt.setInt(1, show_id);
		      ResultSet rs = stmt.executeQuery();
		      while(rs.next()){
		    	  Role role = new Role(
			    		  rs.getInt("person_id"),
			    		  rs.getString("name"),
			    		  rs.getInt("role"),
			    		  show_id,
			    		  rs.getString("character_name"),
			    		  rs.getString("image")
			    		  );
		    	  role.setRoleId(rs.getInt("role_id"));
		    	  list.add(role);
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

	@Override
	public void addRole(Role role) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("INSERT INTO roles(person_id,role,show_id,character_name)"
		      		+ "VALUES(?,?,?,?);");
		      stmt.setInt(1, role.getPerson_id());
		      stmt.setInt(2, role.getRole());
		      stmt.setInt(3, role.getShow_id());
		      stmt.setString(4, role.getCharacter_name());
		      
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
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		System.out.println("Not yet implemented");
	}

	@Override
	public void deleteRole(Role role) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("DELETE FROM roles WHERE role_id=? ;");
		      stmt.setInt(1, role.getRoleId());
		      
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
	public void deleteRolesOfPerson(Person person) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.prepareStatement("DELETE FROM roles WHERE person_id=?;");
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

}
