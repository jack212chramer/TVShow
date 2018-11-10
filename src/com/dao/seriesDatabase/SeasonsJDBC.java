package com.dao.seriesDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.config.ConfigJDBC;

public class SeasonsJDBC extends ConfigJDBC implements SeasonsDao{

	@Override
	public ArrayList<Season> getSeasons(int tmdb_id) {
		 Connection c = null;
		   Statement stmt = null;
		   int numberOfSeasons = howManySeasons(tmdb_id);
		   ArrayList<Season> seasons = new ArrayList<>();
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      
			      for(int i=1;i<=numberOfSeasons;i++){
			    	  
			    	  ArrayList<Episode> episodes = new ArrayList<>();
			    	  
				      stmt = c.createStatement();
				      ResultSet rs = stmt.executeQuery( "SELECT * FROM episodes WHERE tmdb_id ="+tmdb_id+" "
				      		+ " AND season="+i+" ORDER BY episode_number ASC;" );
				      
				      int episodeNumber = 0;
				      while ( rs.next() ) {
				    	  
				    	  episodeNumber++;
				    	  
				    	  Episode episode = new Episode(
				    			  tmdb_id,
				    			  i,
				    			  episodeNumber,
				    			  rs.getString("name"),
				    			  rs.getString("overview"),
				    			  rs.getString("air_date")
				    			  );
				    	  
				    	  episodes.add(episode);
				      }
				      Season season = new Season(episodes);
				      seasons.add(season);
				      
				      rs.close();
				      stmt.close();
			      }
			      
			      c.close();
			      } catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return seasons;
	}

	@Override
	public int howManySeasons(int id) {
		Connection c = null;
		   Statement stmt = null;
		   int numberOfSeasons = 0;
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery( "SELECT season FROM episodes WHERE tmdb_id ="+id+""
			      		+ " ORDER BY season DESC LIMIT 1;" );
			      
			      try{
			    	  numberOfSeasons = rs.getInt("season");
			      }catch(Exception e){
			    	  numberOfSeasons = 0;
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			      } catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return numberOfSeasons;
	}

}
