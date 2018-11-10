<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>TVSeries</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/tvShows.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/tvShows.css">
<link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
   <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
 <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
</head>
<body>
<script>
  AOS.init();
</script>
<div id="includeHeader"></div>


<c:set var="list" value="${results.getList()}" scope="page" />

<div class="results_container">
 <c:set var="i" value="0"/>
<c:forEach items="${list}" var="innerList">

<div data-aos="fade-up"
     data-aos-anchor-placement="bottom-bottom" data-aos-delay="${i}" class="container results_new">
<a class="sortBySelectorHref" href="serial?tmdb_id=${innerList.getTmdb_id()}">
    <div class="row results_new_cont">
        <div class="col-sm-12 col-md-2">
        	<div class="results_details_new">
	        	<img onerror="this.src='resources/images/nopicture.gif'" src="${innerList.getPoster_path()}">
		       </div>
			</div>
       
        <div class="col-sm-12 col-md-10">
        	<div class="results_details_big_new"><h3><c:out value="${innerList.getName()}" /></h3><p>Next episode air date: <br><c:out value="${innerList.getNext_air_date()}" /></p>
        	</div>
        	<div class="results_details_big_new">
				
        </div>
    </div>
    </div>
    </a>
</div>
 <c:set var="i" value="${i+25}"/>
</c:forEach>
</div>

<c:if test="${results.getPages()>1}">
<div id="pagination">
	<div class="pagination">
	  <a href="<c:out value="?page=1"></c:out>">&laquo;</a>
	  
	   <c:if test = "${results.getPage() <= 5}">
	  	 <c:set var="startPage" value="1" scope="page" />
	   </c:if>
	   
	   <c:if test = "${results.getPage() > 5}">
	  	 <c:set var="startPage" value="${results.getPage()-4}" scope="page" />
	   </c:if>
	   
	  <c:forEach var = "i" begin = "${startPage}" end = "${results.getPage()}">
	  <a  href="<c:out value="?page=${i}"></c:out>"><c:out value="${i}"></c:out></a>
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
	  <a  href="<c:out value="?page=${j}"></c:out>"><c:out value="${j}"></c:out></a>
	  </c:forEach>
	  <a href="<c:out value="?page=${results.getPages()}"></c:out>">&raquo;</a>
	</div>
</div>
</c:if>
<!-- -->
<div id="includeFooter"></div>
 

</body>
</html>