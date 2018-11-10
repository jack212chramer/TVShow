package com.dao.userDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.config.ConfigJDBC;

public class CommentJDBC extends ConfigJDBC implements CommentDao{

	@Override
	public Comment getComment(int comment_id) {
		Connection c = null;
		 PreparedStatement stmt = null;
		 Comment comment = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("SELECT * FROM comments,users WHERE"
		      		+ " comments.user_id=users.user_id AND comment_id = ?;");
		      stmt.setInt(1, comment_id);
		      
		      ResultSet rs = stmt.executeQuery();
		    	  		comment = new Comment(
		    			  rs.getInt("comment_id"),
		    			  rs.getInt("user_id"),
		    			  rs.getInt("tmdb_id"),
		    			  rs.getString("text"),
		    			  rs.getString("date"),
		    			  rs.getString("login"),
		    			  rs.getInt("type")
		    			  );		    	
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //  System.exit(0);
		   }
		return comment;
	}

	@Override
	public void addComment(Comment comment) {
		Connection c = null;
		 PreparedStatement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("INSERT INTO comments(user_id,tmdb_id,text,date,type)values("
		      		+ "?,?,?,?,?);");
		      stmt.setInt(1, comment.getUser_id());
		      stmt.setInt(2, comment.getId());
		      stmt.setString(3, comment.getText());
		      stmt.setString(4, comment.getDate());
		      stmt.setInt(5, comment.getType());
		      
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
	public void updateComment(Comment comment) {
		System.out.println("Not yet implemented");
		
	}

	@Override
	public void deleteComment(Comment comment) {
		Connection c = null;
		 PreparedStatement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("DELETE FROM comments WHERE comment_id=?;");
		      stmt.setInt(1, comment.getComment_id());
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
	public ArrayList<Comment> getCommentsForShow(int tmdb_id) {
		Connection c = null;
		 PreparedStatement stmt = null;
		 ArrayList<Comment> list = new ArrayList<>();
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("SELECT * FROM comments,users WHERE"
		      		+ " comments.user_id=users.user_id AND tmdb_id = ? AND type=0;");
		      stmt.setInt(1, tmdb_id);
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while ( rs.next() ) {
		    	  Comment comment = new Comment(
		    			  rs.getInt("comment_id"),
		    			  rs.getInt("user_id"),
		    			  tmdb_id,
		    			  rs.getString("text"),
		    			  rs.getString("date"),
		    			  rs.getString("login"),
		    			  rs.getInt("type")
		    			  );
			    list.add(comment);
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

	@Override
	public ArrayList<Comment> getCommentsForNews(int news_id) {
		Connection c = null;
		 PreparedStatement stmt = null;
		 ArrayList<Comment> list = new ArrayList<>();
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(connectionPath);
		      c.setAutoCommit(false);
		      
		      stmt = c.prepareStatement("SELECT * FROM comments,users WHERE"
		      		+ " comments.user_id=users.user_id AND tmdb_id = ? AND type = 1;");
		      stmt.setInt(1, news_id);
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while ( rs.next() ) {
		    	  Comment comment = new Comment(
		    			  rs.getInt("comment_id"),
		    			  rs.getInt("user_id"),
		    			  news_id,
		    			  rs.getString("text"),
		    			  rs.getString("date"),
		    			  rs.getString("login"),
		    			  rs.getInt("type")
		    			  );
			    list.add(comment);
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
