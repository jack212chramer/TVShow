<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>Search results</title>
<script src="resources/scripts/searchResults.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/searchResults.css">
<script src="resources/scripts/searchResults.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
 <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
 <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
</head>
<body style="margin: 0;">
<script>
  AOS.init();
</script>
	<!--MAIN HEADER LOGIN/REGISTER  -->
	<div id="includeHeader"></div>
	
	<!-- DISPLAY SEARCH RESULTS -->
	<c:set var="results" value="${container.getList() }" scope="page" />
		<c:set var="count" value="0" scope="page" />

	<div >
	<div id="results_1">
		<h3 class="result_text">Znalezione wyniki: <c:out value="${container.getAllShowsInDatabase()}"/></h3>
	</div>
</div>

	<c:forEach items="${results}" var="innerList">

	
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
      
      <!-- PAGINATION -->
      <c:set var="results" value="${container }" scope="page" />
      <c:if test="${results.getPages()>1}">
<div id="pagination">
	<div class="pagination">
	  <a href="<c:out value="?search=${results.getSearch()}&page=1"></c:out>">&laquo;</a>
	  
	   <c:if test = "${results.getPage() <= 5}">
	  	 <c:set var="startPage" value="1" scope="page" />
	   </c:if>
	   
	   <c:if test = "${results.getPage() > 5}">
	  	 <c:set var="startPage" value="${results.getPage()-4}" scope="page" />
	   </c:if>
	   
	  <c:forEach var = "i" begin = "${startPage}" end = "${results.getPage()}">
	  <a  href="<c:out value="?search=${results.getSearch()}&page=${i}"></c:out>"><c:out value="${i}"></c:out></a>
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
	  <a  href="<c:out value="?search=${results.getSearch()}&page=${j}"></c:out>"><c:out value="${j}"></c:out></a>
	  </c:forEach>
	  <a href="<c:out value="?search=${results.getSearch()}&page=${results.getPages()}"></c:out>">&raquo;</a>
	</div>
</div>
</c:if>
      
      
      
   <div id="includeFooter"></div>   
</body>
</html>