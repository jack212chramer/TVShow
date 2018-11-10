<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit show</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
  <link rel="stylesheet" type="text/css" href="resources/css/base.css">
 <script src="resources/scripts/headerAndFooter.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/serial.css">
<script src="resources/scripts/serial.js"></script>
</head>
<body style="margin: 0;);" >
<div id="includeHeader"></div>
	


	<div id="main_info_page_container">
<div >
		<div id="main_info_page">
				<div class="container">
	<form action="updateShow" method="post">
  <div style="display:none" class="form-group">
    <label for="id">Id:</label>
    <input type="text" class="form-control" name="id" id="id" value="${results.getTmdb_id()}">
  </div>
  <div class="form-group">
    <label for="name">Title:</label>
    <input type="text" class="form-control" name="name" id="name" value="${results.getName()}">
  </div>
<div class="form-group">
    <label for="original_name">Original title:</label>
    <input type="text" class="form-control" name="original_name" id="original_name" value="${results.getOriginal_name()}">
  </div>
  <div class="form-group">
    <label for="first_air_date">First air date:</label>
    <input type="text" class="form-control" name="first_air_date" id="first_air_date" value="${results.getFirst_air_date()}">
  </div>
   <div class="form-group">
    <label for="last_air_date">Last air date:</label>
    <input type="text" class="form-control" name="last_air_date" id="last_air_date" value="${results.getLast_air_date()}">
  </div>
     <div class="form-group">
    <label for="original_language">Original language:</label>
    <input type="text" class="form-control" name="original_language" id="original_language" value="${results.getOriginal_language()}">
  </div>
   <div class="form-group">
    <label for="number_of_seasons">Number of seasons:</label>
    <input type="number" class="form-control" name="number_of_seasons" id="number_of_seasons" value="${results.getNumber_of_seasons()}">
  </div>
     <div class="form-group">
    <label for="number_of_episodes">Number of episodes:</label>
    <input type="number" class="form-control" name="number_of_episodes" id="number_of_episodes" value="${results.getNumber_of_episodes()}">
  </div>
     <div class="form-group">
    <label for="homepage">Homepage:</label>
    <input type="text" class="form-control" name="homepage" id="homepage" value="${results.getHomepage()}">
  </div>
       <div class="form-group">
    <label for="overview">Overview:</label>
    <textarea class="form-control" name="overview" id="overview">
    ${results.getOverview()}
    </textarea>
  </div>
       <div class="form-group">
    <label for="poster_path">Poster path:</label>
    <input type="text" class="form-control" name="poster_path" id="poster_path" value="${results.getPoster_path()}">
  </div>
         <div class="form-group">
    <label for="backdrop_path">Backdrop path:</label>
    <input type="text" class="form-control" name="backdrop_path" id="backdrop_path" value="${results.getBackdrop_path()}">
  </div>
  <button type="submit" class="btn bbtn">Update</button>
</form>
		 
</div>
</div>
</div>
</div>

<div id="includeFooter"></div>
</body>
</html>