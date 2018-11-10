package com.dao.news;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.text.html.*; 

public class News extends HTMLEditorKit.ParserCallback{

	private int id;
	private String header;
	private String imageSource;
	private String text;
	private int author_id;
	private String authorName;
	private String imageDescription;
	private String date;
	
	
	
	public News(int id, String header, String imageSource, String text, int author_id, String authorName,
			String imageDescription, String date) {
		super();
		this.id = id;
		this.header = header.trim();
		this.imageSource = imageSource;
		this.text = convertToHtml(text);
		this.author_id = author_id;
		this.authorName = authorName;
		this.imageDescription = imageDescription;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	private String convertToHtml(String s){
		
		s=convertBr(s);
		s=trimBr(s);
		s.trim();
		//s=convertUrl(s);
		return s;
	}
public String convertBr(String string){
    BufferedReader st = new BufferedReader( new StringReader( string ) );
    StringBuffer buf = new StringBuffer( "" );

    try
    {
      String str = st.readLine();

      while( str != null )
      {
        if( str.equalsIgnoreCase( "<br/>" ) )
        {
          str = "<br>";
        }

        buf.append( str );

        if( !str.equalsIgnoreCase( "<br>" ) )
        {
          buf.append( "<br>" );
        }

        str = st.readLine();
      }
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }
    string = buf.toString();
    return string;
	}

public String convertUrl(String plain){
	String str = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?«»“”‘’]))";
	Pattern patt = Pattern.compile(str);
	Matcher matcher = patt.matcher(plain);
	plain = matcher.replaceAll("<a href=\"$1\">$1</a>");
	return plain;
}
public String trimBr(String str){
	if (str != null && str.length() > 0 ) {
        str = str.substring(0, str.length() - 4);
    }
    return str;
}


}
