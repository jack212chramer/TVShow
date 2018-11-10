      <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    
        <script>
  AOS.init();
</script>
 <c:if test="${container.getList().size() > 0}">
 <div id="highestRated" class="row lastWatched overlay_container">
        <div class="col-4 col-md-2">
        	<p>Highest rated: </p>
        	
        </div>
        <c:set var="i" value="0"/>
       
        <c:forEach items="${container.getList()}" var="show"> 
        <div data-aos="flip-left" data-aos-delay="${i}" class="col-4 col-md-2 ">
        	<img  onerror="this.src='resources/images/nopicture.gif'" src="${show.getPoster_path()}">
		       
		<c:if test="${show.getRating()>7}">
     	<c:set var="clr" value="style='color:green;'"/></c:if>
     	<c:if test="${show.getRating()<=7}">
     	<c:set var="clr" value="style='color:orange;'"/></c:if>
     	<c:if test="${show.getRating()<5}">
     	<c:set var="clr" value="style='color:red;'"/></c:if>
      	
      	<c:if test="${show.getRating() != 0}">
        	<div class="user_rating"><span  id="highestRated_rating1" ${clr}>${show.getRatingFormatted2()}</span></div>
        	</c:if>
        	<a id="highestRated_href1" href="serial?tmdb_id=${show.getTmdb_id()}"><div class="overlay"><h3 id="highestRated_title1">${show.getName()}</h3></div></a>
        </div>
         <c:set var="i" value="${i+50}"/>
        </c:forEach>
        
    </div>
    
    </c:if>