<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

      <!DOCTYPE html>
      
      
      <div class="container seasons_container">
     
  <h2>Episodes:</h2>
  <c:if test="${seasons.size()==0}">
  <p>No data</p>
  </c:if>
   <div class="panel-group" id="accordion">
   <c:set var="i" scope="request" value="1"/>
   <c:forEach items="${seasons}" var="season">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <button class="season-link ibtn" data-toggle="collapse" data-parent="#accordion" href="#collapse${i}">Season ${i}</button>
        </h4>
      </div>
      <div id="collapse${i}" class="panel-collapse collapse in">
        <div class="panel-body">
        <br>
 			<c:forEach items="${season.getList()}" var="episode">
 			<h3 class="episode_title">${episode.getEpisode_number()} - ${episode.getName()}</h3>
 			<span class="episode_date">${episode.getAir_date()}</span>
 			<br>
 			<p class="episode_overview">${episode.getOverview()}</p>
 			<hr>
 			</c:forEach>       
        
        </div>
      </div>
    </div>
     <c:set var="i" scope="request" value="${i+1}"/>
    </c:forEach>
    
  
    
  </div> 
</div>