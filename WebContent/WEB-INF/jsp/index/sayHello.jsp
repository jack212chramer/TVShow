      <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
  AOS.init();
</script>
<div id="top5">
		<div class="top5_text"></div>
		<h3>Say hello to new shows</h3>
		<p id="top5_p">Get personal recommendations for what to watch next, plus see what your friends are watching too!</p>
<c:set var="i" value="0"/>

      <c:forEach items="${top5}" var="innerList">
	<a  href="serial?tmdb_id=${innerList.getTmdb_id()}">
	<div data-aos="fade-left" data-aos-delay="${i}">
	<img class="top5img" onerror="this.src='resources/images/nopicture.gif'" src="${innerList.getPoster_path()}">
		   </div></a>
		  <c:set var="i" value="${i+100}"/>
	</c:forEach>
	
	
		
	</div>