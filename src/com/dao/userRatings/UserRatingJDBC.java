package com.dao.userRatings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.config.ConfigJDBC;
import com.dao.seriesDatabase.TVShow;

public class UserRatingJDBC extends ConfigJDBC implements UserRatingDao{
	public  String getShowStatus(String tmdb_id,String user_id) {
		 Connection c = null;
		 Statement stmt = null;
		   String status = null;
		   try {
			   
			   Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT status FROM personalseries WHERE tmdb_id="+tmdb_id+" AND user_id="+user_id+" ;" );
		      
		      while ( rs.next() ) {
		    	  status = rs.getString("status");
		      }
		      
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		return status;
	     
   }
	public  void setShowStatus(String tmdb_id,String user_id,String status) {
		if(status.equals("0")){
			deleteShowStatus(tmdb_id,user_id);
		}else{
				if(idVerification(tmdb_id,user_id)){
					if(getShowStatus(tmdb_id,user_id)==null){
						//System.out.println("insert");
						insertShowStatus(tmdb_id,user_id,status);
					}else{
						//System.out.println("update");
						updateShowStatus(tmdb_id,user_id,status);
					}
				}
			}
	}
	public  boolean idVerification(String tmdb_id,String user_id){
		if(tmdb_id!=null&&user_id!=null){
			return true;
		}else{
			return false;
		}
		
	}
	public  void deleteShowStatus(String tmdb_id,String user_id) {
		 Connection c = null;
		 Statement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);

		   c.setAutoCommit(false);
		      stmt = c.createStatement();
		      String sql="DELETE FROM personalseries WHERE tmdb_id="+tmdb_id+" AND user_id="+user_id+";";
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
			  } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			    //  System.exit(0);
			   }	 
	}
	
	public  String getRating(String tmdb_id,String user_id) {
		 Connection c = null;
		 Statement stmt = null;
		   String status = null;
		   try {
			   
			   Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT vote FROM personalseries WHERE tmdb_id="+tmdb_id+" AND user_id="+user_id+" ;" );
		      
		      while ( rs.next() ) {
		    	  status = rs.getString("vote");
		      }
		      
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		return status;
	     
  }
	
	public  void updateRating(String tmdb_id,String user_id,String rating) {
		 Connection c = null;
		 Statement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);
		  c.setAutoCommit(false);
	      stmt = c.createStatement();
	      String sql="UPDATE personalseries SET vote = '"+rating+"' WHERE tmdb_id="+tmdb_id+" AND user_id="+user_id+";";
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
		  } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }	 
	}
	
	public  void updateShowStatus(String tmdb_id,String user_id,String status) {
		 Connection c = null;
		 Statement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);
		  c.setAutoCommit(false);
	      stmt = c.createStatement();
	      String sql="UPDATE personalseries SET status = '"+status+"' WHERE tmdb_id="+tmdb_id+" AND user_id="+user_id+";";
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
		  } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }	 
	}
	
	public  void insertShowStatus(String tmdb_id,String user_id,String status) {
		 Connection c = null;
		 PreparedStatement stmt = null;
		   try {
			   Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection(connectionPath);
			      c.setAutoCommit(false);
			         
			         stmt = c.prepareStatement("INSERT INTO personalseries(user_id,tmdb_id,status)VALUES(?,?,?);");
				      stmt.setString(1, user_id);
				      stmt.setString(2, tmdb_id);
				      stmt.setString(3, status);
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
	public ArrayList<Double> getAllRatings(int tmdb_id) {
		 Connection c = null;
		 Statement stmt = null;
		 ArrayList<Double> list = new ArrayList<>();;
		   try {
			   
			   Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT vote FROM personalseries WHERE tmdb_id="+tmdb_id+";" );
		      
		      while ( rs.next() ) {
		    	  list.add(rs.getDouble("vote"));
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
	
	public  ArrayList<TVShow> getRecentlyWatched(String user_id){
		
		Connection c = null;
		 Statement stmt = null;
		 Statement stmt2 = null;
		 ArrayList<TVShow> tvShowList = new ArrayList<>();
		   int id;
		   String poster_path=null;
		   String title=null;
		   double rating;
		   try {
			   
			   Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM personalseries WHERE user_id="+user_id+" ORDER BY id DESC LIMIT 5 ;" );
		      
		      while ( rs.next() ) {
		    	  
		    	  id = rs.getInt("tmdb_id");
		    	  rating = rs.getDouble("vote");
		    	  
		    	  stmt2 = c.createStatement();
		    	  ResultSet rs_serialeTable = stmt2.executeQuery( "SELECT * FROM tvshows WHERE tmdb_id="+id+";" );
		    	  poster_path = rs_serialeTable.getString("poster_path");
		    	  title=rs_serialeTable.getString("name");
		    	  rs_serialeTable.close();
		    	  TVShow tvShow = new TVShow(id,poster_path,rating,title);    	
		    	  tvShowList.add(tvShow);
		    	  
		    	 
		      }
		       rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		   return tvShowList;
	}
	
	public ArrayList<TVShow> getHighestRated(String user_id,int limit,int offset){
		
		Connection c = null;
		 Statement stmt = null;
		 Statement stmt2 = null;
		 ArrayList<TVShow> tvShowList = new ArrayList<>();
		   int id;
		   String poster_path=null;
		   String title=null;
		   double rating;
		   try {
			   
			   Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM personalseries WHERE user_id="+user_id+" ORDER BY vote DESC LIMIT "+limit+" OFFSET "+offset+";" );
		      
		   //   int recentlyWatchedShowIdNr=0;
		      while ( rs.next() ) {
		    	  
		    	//  recentlyWatchedShowIdNr++;
		    	  id = rs.getInt("tmdb_id");
		    	  rating = rs.getDouble("vote");
		    	  
		    	  stmt2 = c.createStatement();
		    	  ResultSet rs_serialeTable = stmt2.executeQuery( "SELECT * FROM tvshows WHERE tmdb_id="+id+";" );
		    	  poster_path = rs_serialeTable.getString("poster_path");
		    	  title=rs_serialeTable.getString("name");
		    	  rs_serialeTable.close();
		    	  TVShow tvShow = new TVShow(id,poster_path,rating,title);    	
		    	  tvShowList.add(tvShow);
		    	//  recentlyWatched.setRecentlyWatchId(recentlyWatchedShowIdNr,id,poster_path,rating,title);
		    	
		      }
		   //   recentlyWatched.setSize(recentlyWatchedShowIdNr);
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		//return recentlyWatched;
		   return tvShowList;
	}
}
