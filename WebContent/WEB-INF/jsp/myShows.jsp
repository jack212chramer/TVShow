<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Shows</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/myShows.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
 <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
 
 <meta name="viewport" content="width=device-width; initial-scale=1.0"> 
 
</head>
<body>
<script>
  AOS.init();
</script>
<div id="includeHeader"></div>

<div  class="container ">
  <div class="col-12">
        	<h3>TV series watched by ${container.getUsername()}</h3>
        </div>
         <c:set var="results" value="${container.getList() }" scope="page" />
        <c:if test="${results.size()==0}">
        	<h2> You have not seen any tv show yet</h2>
        </c:if>
	      <div id="highestRated" class="row lastWatched overlay_container" >
      
       <c:set var="i" value="0"/>
       <c:forEach items="${results}" var="innerList">
        <div data-aos="flip-left" data-aos-delay="${i}" class="col-4 col-md-2 ">
        	<img onerror="this.src='resources/images/nopicture.gif'" src="${innerList.getPoster_path()}">
		       <c:if test="${innerList.getRating()>7}">
     	<c:set var="clr" value="style='color:green;'"/></c:if>
     	<c:if test="${innerList.getRating()<=7}">
     	<c:set var="clr" value="style='color:orange;'"/></c:if>
     	<c:if test="${innerList.getRating()<5}">
     	<c:set var="clr" value="style='color:red;'"/></c:if>
        	
        	
        	<c:if test="${innerList.getRating()>0}">
        	<div class="user_rating"><span id="highestRated_rating1" ${clr}>${innerList.getRatingFormatted2()}</span></div>
        	</c:if>
        	<a id="highestRated_href1" href="serial?tmdb_id=${innerList.getTmdb_id()}"><div class="overlay"><h3 id="highestRated_title1">${innerList.getName()}</h3></div></a>
        </div>
        <c:set var="i" value="${i+50}"/>
 	</c:forEach>
    </div>
</div>

 <!-- PAGINATION -->
      <c:set var="results" value="${container}" scope="page" />
      <c:if test="${results.getPages()>1}">
<div id="pagination">
	<div class="pagination">
	  <a href="<c:out value="?user_id=${results.getUser_id()}&page=1"></c:out>">&laquo;</a>
	  
	   <c:if test = "${results.getPage() <= 5}">
	  	 <c:set var="startPage" value="1" scope="page" />
	   </c:if>
	   
	   <c:if test = "${results.getPage() > 5}">
	  	 <c:set var="startPage" value="${results.getPage()-4}" scope="page" />
	   </c:if>
	   
	  <c:forEach var = "i" begin = "${startPage}" end = "${results.getPage()}">
	  <a  href="<c:out value="?user_id=${results.getUser_id()}&page=${i}"></c:out>"><c:out value="${i}"></c:out></a>
	  <c:set var="activePage" value="${i}" scope="page" />
	  
	  </c:forEach>
	  
	   <a class="active" href=""><c:out value="${activePage+1}"></c:out></a>
	   
	    <c:if test = "${results.getPages()-results.getPage() >= 5}">
	  	 <c:set var="lastPage" value="${results.getPage()+5}" scope="page" />
	   </c:if>
	   
	   <c:if test = "${results.getPages()-results.getPage() < 5}">
	  	 <c:set var="lastPage" value="${results.getPages()}" scope="page" />
	   </c:if>
	    
	   <c:forEach var = "j" begin = "${activePage+2}" end = "${lastPage}">
	  <a  href="<c:out value="?user_id=${results.getUser_id()}&page=${j}"></c:out>"><c:out value="${j}"></c:out></a>
	  </c:forEach>
	  <a href="<c:out value="?user_id=${results.getUser_id()}&page=${results.getPages()}"></c:out>">&raquo;</a>
	</div>
</div>
</c:if>



<div id="includeFooter"></div>
</body>
</html>