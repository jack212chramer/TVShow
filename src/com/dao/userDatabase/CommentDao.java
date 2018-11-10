package com.dao.userDatabase;

import java.util.ArrayList;

public interface CommentDao {

	public Comment getComment(int comment_id);
	
	public void addComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public void deleteComment(Comment comment);
	
	public ArrayList<Comment> getCommentsForShow(int tmdb_id);

	public ArrayList<Comment> getCommentsForNews(int news_id);
}
