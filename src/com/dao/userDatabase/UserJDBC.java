package com.dao.userDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.ConfigJDBC;

@Repository("userDAO")
public class UserJDBC extends ConfigJDBC implements UserDao{
	
	@Override
	public boolean addUserToDatabase(User user) {
		 Connection c = null;
		   PreparedStatement stmt = null;
		   boolean completed=false;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);	         
		         stmt = c.prepareStatement("INSERT INTO users(login,password,email)VALUES(?,?,?);");
			      stmt.setString(1, user.getUsername());
			      stmt.setString(2, user.getPassword());
			      stmt.setString(3, user.getEmail());
			      stmt.executeUpdate();
		         completed=true;
		     
		      stmt.close();
		      c.commit();
		      c.close();
		      
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		   return completed;
		
	}

	@Override
	public boolean checkIfUsernameAlreadyUsed(String username) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   boolean duplicate=false;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);


		      stmt = c.prepareStatement("SELECT login FROM users WHERE login=? ;");
		      stmt.setString(1, username);
		      ResultSet rs = stmt.executeQuery();
		      
		      while ( rs.next() ) {
		        if(rs.getString("login").equals(username)){
		        	duplicate=true;
		        }
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		   return duplicate;
		
	}
	@Override
	public String getUserId(String username) {
		 Connection c = null;
		 PreparedStatement stmt = null;
		   String user_id=null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("SELECT user_id FROM users WHERE login=? ;");
		      stmt.setString(1, username);
		      ResultSet rs = stmt.executeQuery();
		      
		      while ( rs.next() ) {
		    	  user_id = rs.getString("user_id");
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		return user_id;
		
	}

	@Override
	public User loginUser(User userNotAuthorised) {
		
			User user_authorised = null;
			
			String passwordEnteredByUser = userNotAuthorised.getPassword();
			String id = getUserId(userNotAuthorised.getUsername());
			
			try{
				String password = getUser(id).getPassword();
				if(passwordEnteredByUser.equals(password)){
					
					user_authorised = getUser(id);
				}
			}catch(java.lang.NullPointerException e){
				//ignore
			}
			return user_authorised;
	}

	
	@Override
	public User getUser(String id) {
		 Connection c = null;
		 PreparedStatement stmt = null;
		 User user = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("SELECT * FROM users WHERE user_id=? ;");
		      stmt.setString(1, id);
		      ResultSet rs = stmt.executeQuery();
		      
		      //while ( rs.next() ) {
		    	  String login = rs.getString("login");
		    	  String email = rs.getString("email");
		    	  String password = rs.getString("password");
		    	  int privileges = rs.getInt("privileges");
		    	  int bannedForDays = rs.getInt("banned_for_days");
		     // }
		    	 user = new User(login,password,email);
		    	 user.setId(id);
		    	 user.setPrivileges(privileges);
		    	 user.setBannedForDays(bannedForDays);
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		return user;
	}

	@Override
	public void updateUser(User user) {
		 Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);	         
		         stmt = c.prepareStatement("UPDATE users SET login=?, password=?, email=?, privileges=?, banned_for_days=? WHERE user_id="+user.getId()+";");
			      stmt.setString(1, user.getUsername());
			      stmt.setString(2, user.getPassword());
			      stmt.setString(3, user.getEmail());
			      stmt.setInt(4, user.getPrivileges());
			      stmt.setInt(5, user.getBannedForDays());
			      stmt.executeUpdate();
		     
		      stmt.close();
		      c.commit();
		      c.close();
		      
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }		
	}

	@Override
	public ArrayList<User> getUsers(String sqlCondition) {
		Connection c = null;
		 PreparedStatement stmt = null;
		 ArrayList<User> list = new ArrayList<>();
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("SELECT * FROM users "+sqlCondition+";");
		      ResultSet rs = stmt.executeQuery();
		      
		      while ( rs.next() ) {
		    	  String id = rs.getString("user_id");
		    	  String login = rs.getString("login");
		    	  String email = rs.getString("email");
		    	  String password = rs.getString("password");
		    	  int privileges = rs.getInt("privileges");
		    	  int bannedForDays = rs.getInt("banned_for_days");
		    	  
		    	  
		    	  User user = new User(login,password,email);
			    	 user.setId(id);
			    	 user.setPrivileges(privileges);
			    	 user.setBannedForDays(bannedForDays);
			    list.add(user);
		      }
		    	
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		return list;
	}
}
