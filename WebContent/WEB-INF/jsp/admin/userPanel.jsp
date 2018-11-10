<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/adminPanel.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div id="includeHeader"></div>

<div class="container">
<form name="decForm" action="userPanel" method="post" enctype="utf8">
		          <input  type="text" name="search" placeholder="search by name or id">
		          <input id="submit"  type="submit" value="Search user">
		          </form>          
  <table class="table table-striped">
    <thead>
      <tr>
        <th>id</th>
        <th>Username</th>
        <th>Email</th>
        <th>Status</th>
        <th>Banned for [days]</th>
      </tr>
    </thead>
    <tbody>
    <c:set var="list" value="${container.getList()}" scope="page" />
<c:forEach items="${list}" var="innerList">        
      <tr>
        <td><c:out value="${innerList.getId()}"/> </td>
        <td><a href="myShows?user_id=${innerList.getId()}"><c:out value="${innerList.getUsername()}"/></a></td>
        <td><c:out value="${innerList.getEmail()}"/></td>
        <td>             <c:if test="${innerList.getPrivileges()>0}">
        	<strong>ADMIN</strong>
            	<form name="decForm" action="decreaseAdminPrivileges" method="post" enctype="utf8">
		          <input style="display:none;" type="text" name="user_id" value="${innerList.getId()}">
		          <input id="submit"  type="submit" value="Decrease admin privileges">
		          </form>
             </c:if>
              <c:if test="${innerList.getPrivileges()==0}">
             <form name="incForm" action="increaseAdminPrivileges" method="post" enctype="utf8">
	          <input style="display:none;" type="text" name="user_id" value="${innerList.getId()}">
	          <input id="submit"  type="submit" value="Increase admin privileges">
	          </form>
             </c:if></td>
         <td><c:out value="${innerList.getBannedForDays()}"/>
         <form name="banForm" action="banUser" method="post" enctype="utf8">
          <input style="display:none;" type="text" name="user_id" value="${innerList.getId()}">
		<span>Ban user for </span>
		<input style="width:50px;" pattern="[0-9]*"  type="text" name="dayCount" title="Only numbers" required>
		<span> days.</span>
		<input id="submit"  type="submit" value="Confirm">
    	</form> </td>
      </tr>
</c:forEach>
    </tbody>
  </table>
</div>


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