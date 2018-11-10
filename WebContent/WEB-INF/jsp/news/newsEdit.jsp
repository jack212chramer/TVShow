<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit article</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/news.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/news.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div id="includeHeader"></div>

<form action="updateNews" method="post">
<div class="container news_article">
    <div class="row ">
        <div class="col-xs-12" style="width:1000px;">
		Article title:<br>
		
		<textarea name="title" rows="1"  cols="50" style="width:1000px;max-height:40px;">
		${news.getHeader()}
		</textarea><br>
		Image source:<br>
		<input type="text" name="img_source" value="${news.getImageSource()}" style="width:1000px;"><br>
		Image description:<br>
		<textarea name="img_desc" rows="1"  cols="50" style="width:1000px;max-height:40px;">
		${news.getImageDescription()}
		</textarea><br>
		Article:<br>
		<textarea name="article_body"  cols="50"style="width:1000px; min-height:500px;">
		${news.getText()}
		</textarea>
		<input type="text" value="${user_id}" name="user_id" style="display:none">
		<input type="text" value="${user}" name="user" style="display:none">
		<input type="text" value="${news.getId()}" name="news_id" style="display:none">
        	<br/><input type="submit" value="Update article">
        	</div>
    </div>
</div>
</form>

<div id="includeFooter"></div>
</body>
</html>