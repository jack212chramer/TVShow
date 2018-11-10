<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>Top 25 seriali</title>
<script src="resources/scripts/searchResults.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
 <link rel="stylesheet" type="text/css" href="resources/css/searchResults.css">
 <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
 <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
</head>
<body style="margin: 0">
<script>
	AOS.init();
</script>
<div id="includeHeader"></div>
	
		<!-- DISPLAY RESULTS -->
	<div id="results_1">
		<h3 class="result_text">Top 25 seriali:</h3>
	</div>

	<c:set var="count" value="0" scope="page" />
	<c:forEach items="${results}" var="innerList">
	<c:set var="count" value="${count + 1}" scope="page"/>
	 
	<div data-aos="fade-up"
     data-aos-anchor-placement="bottom-bottom" class="container row_search">
		<a style="text-decoration: none" class="row_search" href="serial?tmdb_id=${innerList.getTmdb_id()}">
    <div class="row">
        <div class="col-sm-12 col-md-1">
        	<div class="results_details_new">
	        <img onerror="this.src='resources/images/nopicture.gif'" src="${innerList.getPoster_path()}">
		       </div>
			</div>
       
        <div class="col-sm-12 col-md-11">
        	<div class="results_details_big_new">
        		<div id="top25_int"><p><c:out value="${count}" /></p></div>
        	<h3><c:out value="${innerList.getName()}" /></h3>
			<p>(<c:out value="${innerList.getFirst_air_date()}" />)</p>
        	</div>
        	<div class="results_details_big_new">
				<div class="results_details_big_sub_new"><p>Popularity: <c:out value="${innerList.getPopularity()}" /></p></div>
				<div class="results_details_big_sub_new"><p>Original language: <c:out value="${innerList.getOriginal_language()}" /> </p></div>
			
        </div>
    </div>
    </div>
     </a>
</div>
	</c:forEach>

    
<div id="includeFooter"></div>
</body>
</html>