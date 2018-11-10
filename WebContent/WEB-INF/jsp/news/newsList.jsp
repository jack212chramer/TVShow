<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>TVSeries</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/newsList.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/newsList.js"></script>
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

<c:set var = "newsList" scope = "session" value = "${results.getList()}"/>
<c:set var = "newsCount" scope = "session" value = "${newsList.size()}"/>
<div class="container">
    <div class="row">
        <div data-aos="fade-down" class="col-12 ">
        	
        	<div class=" mainNewsBlur" style="background-image: url('${newsList[0].getImageSource()}');" >
        	<a href=<c:out value="news?news_id=${newsList[0].getId() }"/>>
	        		<h3 class=" mainNewsBlurh3">${newsList[0].getHeader() }</h3>
	        </a>
			</div>
        	
        </div>
    </div>

<c:set var = "newsDisplayed" scope = "session" value = "1"/>
 <c:forEach var = "newsNumber" begin = "1" end = "${newsCount}">

<c:if test = "${newsCount-newsDisplayed>=3}">
 <div  class="row">
        <div data-aos="flip-right" class="col-md-4 ">
        	<a href=<c:out value="news?news_id=${newsList[newsDisplayed].getId() }"/>>
        	<div class="smallNews"style="background-image: url('${newsList[newsDisplayed].getImageSource()}');">
        	<h3>${newsList[newsDisplayed].getHeader()}</h3>
        	</div>
        	</a>
        </div>
             <div data-aos="flip-right" class="col-md-4 ">
        	<a href=<c:out value="news?news_id=${newsList[newsDisplayed+1].getId() }"/>>
        	<div class="smallNews" style="background-image: url('${newsList[newsDisplayed+1].getImageSource()}');">
        	<h3>${newsList[newsDisplayed+1].getHeader()}</h3>
        	</div>
        	</a>
        </div>
             <div data-aos="flip-right" class="col-md-4">
        	<a href=<c:out value="news?news_id=${newsList[newsDisplayed+2].getId() }"/>>
        	<div class="smallNews" style="background-image: url('${newsList[newsDisplayed+2].getImageSource()}');">
        	<h3>${newsList[newsDisplayed+2].getHeader()}</h3>
        	</div>
        	</a>
        </div>
    </div> 
    <c:set var = "newsDisplayed" scope = "session" value = "${newsDisplayed+3}"/>
</c:if>

<c:if test = "${newsCount-newsDisplayed==2}">
   <div class="row">
        <div data-aos="fade-up" class="col-md-6 ">
        	<a href=<c:out value="news?news_id=${newsList[newsDisplayed].getId() }"/>>
        	<div class="smallNews" style="background-image: url('${newsList[newsDisplayed].getImageSource()}');">
        	<h3>${newsList[newsDisplayed].getHeader()}</h3>
        	</div>
        	</a>
        </div>
         <div data-aos="fade-up" class="col-md-6 ">
        	<a href=<c:out value="news?news_id=${newsList[newsDisplayed+1].getId() }"/>>
        	<div class="smallNews" style="background-image: url('${newsList[newsDisplayed+1].getImageSource()}');">
        	<h3>${newsList[newsDisplayed+1].getHeader()}</h3>
        	</div>
        	</a>
        </div>
    </div>
    <c:set var = "newsDisplayed" scope = "session" value = "${newsDisplayed+2}"/>
</c:if>

<c:if test = "${newsCount-newsDisplayed==1}">
   <div class="row">
        <div data-aos="fade-up" class="col-12 ">
        	<a href=<c:out value="news?news_id=${newsList[newsDisplayed].getId() }"/>>
        	<div class="smallNews" style="background-image: url('${newsList[newsDisplayed].getImageSource()}');">
        	<h3>${newsList[newsDisplayed].getHeader()}</h3>
        	</div>
        	</a>
        </div>
    </div>
    <c:set var = "newsDisplayed" scope = "session" value = "${newsDisplayed+1}"/>
</c:if>

 
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

<div id="includeFooter"></div>
</body>
</html>