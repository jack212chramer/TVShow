package com.dao.seriesDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.config.ConfigJDBC;

public class SeriesJDBC extends ConfigJDBC implements SeriesDao {
	
	
	@Override
	public TVShow searchById(int id) {
		 Connection c = null;
		   Statement stmt = null;
		   TVShow tvShow = null;
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery( "SELECT * FROM tvshows WHERE tmdb_id ="+id+";" );
			      while ( rs.next() ) {
			
			    	  tvShow = new TVShow(
			    			  rs.getInt("tmdb_id"),
			    			  rs.getString("poster_path"),
			    			  rs.getString("name"),
			    			  rs.getDouble("vote_average"),
			    			  rs.getString("original_name"),
			    			  parseYear(rs.getString("first_air_date")),
			    			  rs.getInt("popularity"),
			    			  rs.getString("original_language"),
			    			  rs.getString("backdrop_path"),
			    			  rs.getString("next_episode_to_air")
			    			  );
			    	  tvShow.setOverview(rs.getString("overview"));
			    	  tvShow.setHomepage(rs.getString("homepage"));
			    	  tvShow.setLast_air_date(rs.getString("last_air_date"));
			    	  tvShow.setNumber_of_episodes(rs.getInt("number_of_episodes"));
			    	  tvShow.setNumber_of_seasons(rs.getInt("number_of_seasons"));
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			      } catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return tvShow;
	}

	@Override
	public ArrayList<TVShow> searchByName(String input,int limit,int offset) {
		Connection c = null;
		   Statement stmt = null;
		   ArrayList<TVShow> tvShowList = new ArrayList<>();
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM tvshows WHERE original_name LIKE'%"+input+"%' OR name LIKE'%"+input+"%' ORDER BY popularity DESC LIMIT "+limit+" OFFSET "+offset+";" );
		      while ( rs.next() ) {	    	  
		    	  TVShow tvShow = new TVShow(
		    			  rs.getInt("tmdb_id"),
		    			  rs.getString("poster_path"),
		    			  rs.getString("name"),
		    			  rs.getDouble("vote_average"),
		    			  rs.getString("original_name"),
		    			  parseYear(rs.getString("first_air_date")),
		    			  rs.getInt("popularity"),
		    			  rs.getString("original_language"),
		    			  rs.getString("backdrop_path"),
		    			  rs.getString("next_episode_to_air")
		    			  );
		    	  tvShowList.add(tvShow);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   return tvShowList;
	}

	@Override
	public int searchByNameCount(String input) {
		Connection c = null;
		   Statement stmt = null;
		   int showCount = 0;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) AS total FROM tvshows WHERE original_name LIKE'%"+input+"%' OR name LIKE'%"+input+"%' ;" );
		      while ( rs.next() ) {	    	  
		    	  showCount=rs.getInt("total");
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   return showCount;
	}
	
	@Override
	public ArrayList<TVShow> getShows(int limit, int type,int offset,boolean descending,String sqlCondition) {
		/**	type:
		 * 		0- sorted by popularity
		 *		1- sorted by vote_average
		 *		2- sorted by name
		 *		3- sorted by language
		 *		4- sorted by number of episodes
		 *		5- sorted by number of seasons
		 *		6- sorted by next air date
		 *
		 */	
		 Connection c = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   ArrayList<TVShow> tvShowList = new ArrayList<>();
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      String query="SELECT * FROM tvshows "+sqlCondition+" ";
		      if(type==0){
		    	  query +="ORDER BY popularity";
		      }else if(type==1){
		    	  query +="ORDER BY vote_average ";
		      }else if(type==2){
		    	  query +="ORDER BY name";
		      }
		      else if(type==3){
		    	  query +="ORDER BY original_language";
		      }
		      else if(type==4){
		    	  query +="ORDER BY number_of_episodes";
		      }
		      else if(type==5){
		    	  query +="ORDER BY number_of_seasons";
		      }
		      else if(type==6){
		    	  query +="ORDER BY first_air_date";
		      }
		      else if(type==7){
		    	  query +="ORDER BY last_air_date";
		      }else if(type==8){
		    	  query +="ORDER BY next_episode_to_air";
		      }
		      
		      if(descending){
		    	  query +=" DESC ";
		      }else{
		    	  query +=" ASC ";
		      }

		      query +="LIMIT "+limit+" OFFSET "+offset+";";
		      rs = stmt.executeQuery(query);
		      while ( rs.next() ) {
		    	  TVShow tvShow = new TVShow(
		    			  rs.getInt("tmdb_id"),
		    			  rs.getString("poster_path"),
		    			  rs.getString("name"),
		    			  rs.getDouble("vote_average"),
		    			  rs.getString("original_name"),
		    			  parseYear(rs.getString("first_air_date")),
		    			  rs.getInt("popularity"),
		    			  rs.getString("original_language"),
		    			  rs.getString("backdrop_path"),
		    			  rs.getString("next_episode_to_air")
		    			  );
		    	  
		    	  tvShowList.add(tvShow);		    	  
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   return tvShowList;
	}

	@Override
	public String parseYear(String data) {
		try{
		data = data.split("-")[0];
		}catch(NullPointerException e){
			//ignore
		}
		return data;
	}

	@Override
	public ArrayList<Integer> getAllTmdb_id() {
		Connection c = null;
		   Statement stmt = null;
		   ArrayList<Integer> data = new ArrayList<>();
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT tmdb_id FROM tvshows;" );
		      while ( rs.next() ) {
		    	 data.add(rs.getInt("tmdb_id"));
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return data;
	}

	@Override
	public void updateAverageRating(int tmdb_id,double average) {
		 Connection c = null;
		 Statement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);
		  c.setAutoCommit(false);
	      stmt = c.createStatement();
	      String sql="UPDATE tvshows SET vote_average = '"+average+"' WHERE tmdb_id="+tmdb_id+";";
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
		  } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }	 
	}

	@Override
	public int countAllSeries(String condition) {
		Connection c = null;
		   Statement stmt = null;
		   int numberOfResults = 0;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) AS total FROM tvshows "+condition+";" );
		      while ( rs.next() ) {
		    	  numberOfResults=rs.getInt("total");
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      } catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return numberOfResults;
	}

	@Override
	public void updateShow(TVShow show) {
		 Connection c = null;
		   PreparedStatement stmt = null;
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      stmt = c.prepareStatement("UPDATE tvshows "
				     		+ " SET  original_name=?,"
				     		+ "name = ?,"
				     		+ "poster_path = ?,"
				     		+ "first_air_date = ?,"
				     		+ "original_language = ?,"
				     		+ "overview = ?,"
				     		+ "homepage = ?,"
				     		+ "last_air_date = ?,"
				     		+ "number_of_episodes = ?,"
				     		+ "number_of_seasons = ?,"
				     		+ "backdrop_path = ?"
				     		+ " WHERE tmdb_id ="+show.getTmdb_id()+";");
			      
			      stmt.setString(1, show.getOriginal_name());
			      stmt.setString(2, show.getName());
			      stmt.setString(3, show.getPoster_path());
			      stmt.setString(4, show.getFirst_air_date());
			      stmt.setString(5, show.getOriginal_language());
			      stmt.setString(6, show.getOverview());
			      stmt.setString(7, show.getHomepage());
			      stmt.setString(8, show.getLast_air_date());
			      stmt.setInt(9, show.getNumber_of_episodes());
			      stmt.setInt(10, show.getNumber_of_seasons());
			      stmt.setString(11, show.getBackdrop_path());
			      
			      
			     stmt.executeUpdate();
			      stmt.close();
			      c.commit();
			      c.close();
			      } catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	@Override
	public void deleteShow(TVShow show) {
		 Connection c = null;
		 Statement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);

		   c.setAutoCommit(false);
		      stmt = c.createStatement();
		      String sql="DELETE FROM tvshows WHERE tmdb_id="+show.getTmdb_id()+";";
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
			  } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			    //  System.exit(0);
			   }	 
	}

	@Override
	public void createShow(TVShow show) {
		 Connection c = null;
		   PreparedStatement stmt = null;
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      stmt = c.prepareStatement("INSERT INTO tvshows "
			    		  	+" (original_name,name,poster_path,first_air_date,popularity,original_language,"
			    		  	+ " overview,homepage,last_air_date,number_of_episodes,number_of_seasons,backdrop_path,vote_average)"
				     		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
			      stmt.setString(1, show.getOriginal_name());
			      stmt.setString(2, show.getName());
			      stmt.setString(3, show.getPoster_path());
			      stmt.setString(4, show.getFirst_air_date());
			      stmt.setInt(5, show.getPopularity());
			      stmt.setString(6, show.getOriginal_language());
			      stmt.setString(7, show.getOverview());
			      stmt.setString(8, show.getHomepage());
			      stmt.setString(9, show.getLast_air_date());
			      stmt.setInt(10, show.getNumber_of_episodes());
			      stmt.setInt(11, show.getNumber_of_seasons());
			      stmt.setString(12, show.getBackdrop_path());
			      stmt.setDouble(13, show.getRating());
			     
			      
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
