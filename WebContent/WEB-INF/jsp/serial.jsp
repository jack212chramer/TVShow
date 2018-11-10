<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title><c:out value="${results.getName()}" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
  <link rel="stylesheet" type="text/css" href="resources/css/base.css">
 <script src="resources/scripts/headerAndFooter.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/serial.css">
<script src="resources/scripts/serial.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/seasons.css">
</head>
<body style="margin: 0;);" >
<script>
	AOS.init();
</script>
<div id="includeHeader"></div>
	

	<div id="main_info_page_container">
<div data-aos="fade-up" >
		<div id="main_info_page">
				<div class="container">
		    <div class="row ">
		        <div class="col-sm-5 col-md-4 main_tile">

					<img class="img-thumbnail" onerror="this.src='resources/images/nopicture.gif'" src="${results.getPoster_path()}">
		        </div>
		        <div class="col-sm-7 col-md-4 main_tile_title">
		        	 
		        	<h3><c:out value="${results.getName()}" /></h3>
					<p style="color:#737373;"><c:out value="${results.getOriginal_name()}" /></p>
					 <p style="padding-top:10px;">First air date: <c:out value="${results.getFirst_air_date()}" /></p>
					  <p>Last air date: <c:out value="${results.getLast_air_date()}" /></p>
					 <p>Original language: <c:out value="${results.getOriginal_language()}" /></p>
					 <p>Number of seasons: <c:out value="${results.getNumber_of_seasons()}" /></p>
					 <p>Number of episodes: <c:out value="${results.getNumber_of_episodes()}" /></p>
					 <c:if test = "${results.getHomepage() != '/'}">
					 <a target="_blank" rel="noopener noreferrer" href=<c:out value="${results.getHomepage()}" />>Visit homepage</a>
		        	</c:if>
		        	</div>
		        	<div class="col-md-4 main_tile_ocena">
		        	<div class="rating bbtn">
		        	<h3>Rating:</h3>
		        	
		        	<h1><c:out value="${results.getRatingFormatted()}" /></h1>
		        	<p><c:out value="Popularity: ${results.getPopularity()}" /></p>
		        	<c:if test = "${user == null}">
		        	<p>Please log-in or register to rate this show.</p>
		        	</c:if>
		        	<c:if test = "${user != null}">
		        	<button id="watched_button" onclick="watchedPressed()">Set as Watched</button>
		        	<div id="rating-stars">
		        		<fieldset class="rating">
    <input onclick="rateSeries(10)" type="radio" id="star10" name="rating" value="10" /><label class = "full" for="star10" ></label>
    <input onclick="rateSeries(9.5)" type="radio" id="star9half" name="rating" value="4 and a half" /><label class="half" for="star9half" ></label>
    <input onclick="rateSeries(9)" type="radio" id="star9" name="rating" value="9" /><label class = "full" for="star9" ></label>
    <input onclick="rateSeries(8.5)" type="radio" id="star8half" name="rating" value="3 and a half" /><label class="half" for="star8half" ></label>
    <input onclick="rateSeries(8)" type="radio" id="star8" name="rating" value="8" /><label class = "full" for="star8" ></label>
    <input onclick="rateSeries(7.5)" type="radio" id="star7half" name="rating" value="2 and a half" /><label class="half" for="star7half" ></label>
    <input onclick="rateSeries(7)" type="radio" id="star7" name="rating" value="7" /><label class = "full" for="star7" ></label>
    <input onclick="rateSeries(6.5)" type="radio" id="star6half" name="rating" value="1 and a half" /><label class="half" for="star6half" ></label>
    <input onclick="rateSeries(6)" type="radio" id="star6" name="rating" value="6" /><label class = "full" for="star6" ></label>
    <input onclick="rateSeries(5.5)" type="radio" id="star5half" name="rating" value="half" /><label class="half" for="star5half" ></label>
    <input onclick="rateSeries(5)" type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" ></label>
    <input onclick="rateSeries(4.5)" type="radio" id="star4half" name="rating" value="4 and a half" /><label class="half" for="star4half" ></label>
    <input onclick="rateSeries(4)" type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" ></label>
    <input onclick="rateSeries(3.5)" type="radio" id="star3half" name="rating" value="3 and a half" /><label class="half" for="star3half" ></label>
    <input onclick="rateSeries(3)" type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" ></label>
    <input onclick="rateSeries(2.5)" type="radio" id="star2half" name="rating" value="2 and a half" /><label class="half" for="star2half" ></label>
    <input onclick="rateSeries(2)" type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" ></label>
    <input onclick="rateSeries(1.5)" type="radio" id="star1half" name="rating" value="1 and a half" /><label class="half" for="star1half" ></label>
    <input onclick="rateSeries(1)" type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" ></label>
    <input onclick="rateSeries(0.5)" type="radio" id="starhalf" name="rating" value="half" /><label class="half" for="starhalf" ></label>
</fieldset>
		        	</div>
		        	</c:if>
		        	</div>
		        	</div>
		        
		        <div class="col-xs-12 description_tile overview">
			        <h2 id="overview_h2">Overview:</h2>
					<p id="overview">${results.getOverview()}</p>
		        </div>
		        
		    </div>
		</div>
		</div>
		</div>
</div>
		<div data-aos-anchor-placement="center-bottom" data-aos-duration="1000"
		data-aos="zoom-out" id="main_info_page_container_img" style="background-image : url('${results.getBackdrop_path()}');">
</div>

<c:if test="${Roles.size()>0}">
<div data-aos="fade-up" data-aos-offset="300" id="creator_info_container">

	<div class="container">

	<c:set var="i" scope="request" value="0"/>
	<c:forEach items="${Roles}" var="role"> 
	<c:if test="${i<3}">	
    <div class="row creator_info">
        <div class="col-3 creator_info_img">
        	<img class="img-thumbnail" src="${role.getImage()}">
        </div>
        <div class="col-5">
        	<a href=""><p>${role.getPerson()}</p></a>
        </div>
        <div class="col-4">
        	<p>as ${role.getCharacter_name()}</p>
        	<c:if test="${user_privileges>0}">
        	<form action="deleteRole">
        	<input type="text" name="role_id" value="${role.getRoleId()}" style="display:none">
        	<input type="submit" value="Delete">
        	</form>
        	</c:if>
        </div>
    </div>
    
    </c:if>
    
    <c:if test="${i>=3}">	
    <div id="showCast" class="collapse">
    <div class="row creator_info">
        <div class="col-3 creator_info_img">
        	<img class="img-thumbnail" src="${role.getImage()}">
        </div>
        <div class="col-5">
        	<a href=""><p>${role.getPerson()}</p></a>
        </div>
        <div class="col-4">
        	<p>as ${role.getCharacter_name()}</p>
        	<c:if test="${user_privileges>0}">
        	<form action="deleteRole">
        	<input type="text" name="role_id" value="${role.getRoleId()}" style="display:none">
        	<input type="submit" value="Delete">
        	</form>
        	</c:if>
        </div>
    </div>

    </div>
    </c:if>
    
    
<c:set var="i" scope="request" value="${i+1}"/>
</c:forEach>

<c:if test="${Roles.size()>3}">
<button style="width:100%" class="btn bbtn" data-toggle="collapse" data-target="#showCast">Show more</button>
</c:if>
</div>

</div>	
</c:if>
    <c:if test="${user_privileges>0}">
    
    <div id="addRoleB" class="collapse">
     <div class="form-group" style="width:50%;margin:auto;">
    Add role
    <form action="addRole">
        	
        	
   <div id="addRole"></div>
   
   	     
        	<span>as </span>
        	<input value="${results.getTmdb_id()}" type="text" name="tmdb_id" style="display:none">
        	<input class="form-control" type="text" name="character_name">
        	<br>
        	<input class="form-control bbtn" type="submit" value="Add role">
        	<br>
        	</form>
        	</div>
       </div>
       <button style="width:100%" class="btn " data-toggle="collapse" data-target="#addRoleB">Add role</button>
    </c:if>


<div id="includeSeasons" class="comment"  data-aos="fade-up"></div>
<div data-aos="fade-up" class="comment" id="includeComments"></div>
<div id="includeFooter"></div>
</body>
</html>