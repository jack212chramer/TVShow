package com.dao.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import com.config.ConfigJDBC;
import com.service.DateAndTimeService;

public class NewsJDBC extends ConfigJDBC implements NewsDao{

	@Autowired
	DateAndTimeService dateAndTimeService;
	
	@Override
	public int getNewsCount() {
		Connection c = null;
		   Statement stmt = null;
		   int numberOfResults = 0;
		   try {
		   Class.forName("org.sqlite.JDBC");
		    
				c = DriverManager.getConnection(connectionPath);
			
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) AS total FROM news ;" );
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
	public ArrayList<News> getNewsList(int limit, int offset) {
		Connection c = null;
		   Statement stmt = null;
		   News news = null;
		   ArrayList<News> newsList = new ArrayList<>();
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery( "SELECT * FROM news ORDER BY news_id DESC LIMIT "+limit+" OFFSET "+offset+";" );
			      while ( rs.next() ) {
			
			    	  news = new News(
			    			  rs.getInt("news_id"),
			    			  rs.getString("news_header"),
			    			  rs.getString("news_image_source"),
			    			  rs.getString("news_text"),
			    			  rs.getInt("news_author_id"),
			    			  rs.getString("news_author_name"),
			    			  rs.getString("image_description"),
			    			  rs.getString("news_date_of_creation")
			    			  );
			    	  newsList.add(news);
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			      } catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return newsList;
	}

	@Override
	public News getNewsWithId(int id) {
		 Connection c = null;
		   Statement stmt = null;
		   News news = null;
		   try {
			   Class.forName("org.sqlite.JDBC");
			    
					c = DriverManager.getConnection(connectionPath);
				
			      c.setAutoCommit(false);
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery( "SELECT * FROM news WHERE news_id ="+id+";" );
			      while ( rs.next() ) {
			
			    	  news = new News(
			    			  rs.getInt("news_id"),
			    			  rs.getString("news_header"),
			    			  rs.getString("news_image_source"),
			    			  rs.getString("news_text"),
			    			  rs.getInt("news_author_id"),
			    			  rs.getString("news_author_name"),
			    			  rs.getString("image_description"),
			    			  rs.getString("news_date_of_creation")
			    			  );
	
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			      } catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return news;
	}

	@Override
	public void insertNews(News news) {
		 Connection c = null;
		   PreparedStatement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);	         
		         stmt = c.prepareStatement("INSERT INTO news(news_header,news_image_source,news_text,"
		         		+ "news_author_id,news_author_name,news_date_of_creation,image_description)VALUES(?,?,?,?,?,?,?);");
			      stmt.setString(1, news.getHeader());
			      stmt.setString(2, news.getImageSource());
			      stmt.setString(3, news.getText());
			      stmt.setInt(4, news.getAuthor_id());
			      stmt.setString(5, news.getAuthorName());
			      stmt.setString(6, dateAndTimeService.getCurrentDateAndTime());
			      stmt.setString(7, news.getImageDescription());
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
	public void deleteNews(int news_id) {
		Connection c = null;
		 Statement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);

		   c.setAutoCommit(false);
		      stmt = c.createStatement();
		      String sql="DELETE FROM news WHERE news_id="+news_id+";";
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
	public void updateNews(int id, News news) {
		 Connection c = null;
		 PreparedStatement stmt = null;
		 try{
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection(connectionPath);
		  c.setAutoCommit(false);
	      stmt = c.prepareStatement("UPDATE news SET news_header=?,news_image_source=?,news_text=?,"
		         		+ "news_author_id=?,news_author_name=?,news_date_of_creation=?,image_description=? WHERE news_id="+id+";");
	      stmt.setString(1, news.getHeader());
	      stmt.setString(2, news.getImageSource());
	      stmt.setString(3, news.getText());
	      stmt.setInt(4, news.getAuthor_id());
	      stmt.setString(5, news.getAuthorName());
	      stmt.setString(6,dateAndTimeService.getCurrentDateAndTime() );
	      stmt.setString(7, news.getImageDescription());
	      stmt.executeUpdate();
	      stmt.close();
	      c.commit();
	      c.close();
		  } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }	 
		
	}

}
